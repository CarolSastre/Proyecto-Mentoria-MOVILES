package com.example.mentoria.features.auth.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.mentoria.core.domain.model.Usuario
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking

// Se cambia el nombre para forzar la creación de un nuevo almacén de datos y limpiar datos corruptos.
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings_v2")

class SessionManager(
    context: Context
): AuthLocalDataSource {

    private val _events = Channel<SessionEvent>()
    val events = _events.receiveAsFlow()
    private val dataStore = context.dataStore

    // Caché en memoria para el token.
    @Volatile
    private var inMemoryToken: String?

    private val _user = MutableStateFlow<Usuario?>(null)
    val userFlow: StateFlow<Usuario?> = _user.asStateFlow()

    init {
        // Inicializa el token en memoria desde DataStore.
        // runBlocking aquí es aceptable porque se ejecuta una sola vez durante la inyección
        // de dependencias, antes de que se realicen peticiones de red.
        inMemoryToken = runBlocking { getTokenFlow().firstOrNull() }
    }

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
    }

    /**
     * Devuelve el token desde la caché en memoria. Es síncrono y seguro para llamar
     * desde el AuthInterceptor.
     */
    fun getInMemoryToken(): String? = inMemoryToken

    override suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
        // Actualiza la caché en memoria.
        inMemoryToken = token
    }

    fun setCurrentUser(user: Usuario?) {
        _user.value = user
    }

    // Guardar Token y ID juntos
    suspend fun saveSession(token: String, userId: String?) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
            preferences[USER_ID_KEY] = userId ?: ""
        }
        // Actualiza la caché en memoria.
        inMemoryToken = token
    }

    suspend fun clearSession() {
        dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
            preferences.remove(USER_ID_KEY)
        }
        // Actualiza la caché en memoria.
        inMemoryToken = null
    }

    override fun getTokenFlow(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

    override suspend fun getToken(): String? {
        return dataStore.data.map { it[TOKEN_KEY] }.firstOrNull()
    }

    override suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
        // Actualiza la caché en memoria.
        inMemoryToken = null
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
