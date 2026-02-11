package com.example.mentoria.features.auth.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class AuthLocalDataSourceDataStoreImpl(
    private val dataStore: DataStore<Preferences>
): AuthLocalDataSource {
    private object AuthPreferencesKeys {
        val TOKEN = stringPreferencesKey("auth_token")
    }

    override suspend fun saveToken(token: String) {
        dataStore.edit { prefs ->
            prefs[AuthPreferencesKeys.TOKEN] = token
        }
    }

    override suspend fun getToken(): String? {
        return dataStore.data.map { preferences ->
            preferences[AuthPreferencesKeys.TOKEN]
        }.firstOrNull()
    }

    override suspend fun clearToken() {
        dataStore.edit { it.clear() }
    }
}
