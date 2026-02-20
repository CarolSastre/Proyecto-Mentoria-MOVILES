package com.example.mentoria.features.auth.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.mentoria.core.domain.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings_v2")

class SessionManager(
    context: Context
) : AuthLocalDataSource {

    private val dataStore = context.dataStore
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    // Caché en memoria para el token.
    @Volatile
    private var inMemoryToken: String? = null

    private val _events = Channel<SessionEvent>()
    val events = _events.receiveAsFlow()


    private val _user = MutableStateFlow<Usuario?>(null)
    val userFlow: StateFlow<Usuario?> = _user.asStateFlow()

    init {
        // Al iniciar, carga el token desde DataStore a la caché en memoria
        scope.launch {
            inMemoryToken = getTokenFlow().firstOrNull()
            println("SessionManager init: Token cargado desde DataStore: $inMemoryToken")
        }
    }

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
    }

    override suspend fun saveToken(token: String) {
        // Guardar en la caché y en DataStore
        inMemoryToken = token
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    fun setCurrentUser(user: Usuario?) {
        _user.value = user
    }

    suspend fun saveSession(token: String, userId: String?) {
        println("Token del usuario guardado: $token")
        // 1. Guardar en caché para acceso inmediato
        inMemoryToken = token
        // 2. Persistir en DataStore para futuras sesiones
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
            preferences[USER_ID_KEY] = userId ?: ""
        }
    }

    suspend fun clearSession() {
        // Limpiar caché y DataStore
        inMemoryToken = null
        dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
            preferences.remove(USER_ID_KEY)
        }
    }

    override fun getTokenFlow(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

    /**
     * Devuelve el token de la caché en memoria de forma síncrona.
     * Ideal para el `AuthInterceptor` que no puede ser suspend.
     */
    fun getTokenFromMemory(): String? {
        println("getTokenFromMemory: Devolviendo token desde memoria: $inMemoryToken")
        return inMemoryToken
    }

    /**
     * Implementación de la interfaz. Devuelve el token, priorizando la caché
     * y consultando DataStore si es necesario.
     */
    override suspend fun getToken(): String? {
        return inMemoryToken ?: getTokenFlow().firstOrNull().also { loadedToken ->
            inMemoryToken = loadedToken
        }
    }


    override suspend fun clearToken() {
        clearSession()
        setCurrentUser(null)
    }

    suspend fun notifyLoggedOut() {
        clearToken()
        _events.send(SessionEvent.LoggedOut)
    }

    val userIdFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[USER_ID_KEY]
    }
}
