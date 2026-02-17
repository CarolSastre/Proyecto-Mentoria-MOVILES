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

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SessionManager(
    context: Context
): AuthLocalDataSource {

    private val _events = Channel<SessionEvent>()
    val events = _events.receiveAsFlow()
    private val dataStore = context.dataStore

    private val _user = MutableStateFlow<Usuario?>(null)
    val userFlow: StateFlow<Usuario?> = _user.asStateFlow()

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
    }

    override suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
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
    }

    suspend fun clearSession() {
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

    // 2. Para el Interceptor (Síncrono/Suspend)
    override suspend fun getToken(): String? {
        return dataStore.data.map { it[TOKEN_KEY] }.firstOrNull()
    }

    override suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
        setCurrentUser(null)
    }

    suspend fun notifyLoggedOut() {
        _events.send(SessionEvent.LoggedOut)
    }

    // Mejor expongamos el Flow del ID
    val userIdFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[USER_ID_KEY]
    }
}
