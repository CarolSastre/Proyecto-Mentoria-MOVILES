package com.example.mentoria.features.auth.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.mentoria.features.auth.data.local.AuthLocalDataSourceDataStoreImpl.AuthPreferencesKeys
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SessionManager(context: Context): AuthLocalDataSource {

    private val _events = Channel<SessionEvent>()
    val events = _events.receiveAsFlow()
    private val dataStore = context.dataStore

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
    }

    override suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    override fun getTokenFlow(): Flow<String?> {
        return dataStore.data.map { it[TOKEN_KEY] }
    }

    // 2. Para el Interceptor (Síncrono/Suspend)
    override suspend fun getToken(): String? {
        return dataStore.data.map { it[TOKEN_KEY] }.firstOrNull()
    }

    override suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }

    suspend fun notifyLoggedOut() {
        _events.send(SessionEvent.LoggedOut)
    }
}