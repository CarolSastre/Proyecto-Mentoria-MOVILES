package com.example.mentoria.core.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.mentoria.core.domain.repositories.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : DataStoreRepository {

    private object PreferencesKeys {
        val TOKEN_KEY = stringPreferencesKey("auth_token")
    }

    override suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.TOKEN_KEY] = token
        }
    }

    override fun getToken(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[PreferencesKeys.TOKEN_KEY]
        }
    }
}