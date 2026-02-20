package com.example.mentoria.features.auth.data.local

import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {
    suspend fun saveToken(token: String)
    fun getTokenFlow(): Flow<String?>
    suspend fun getToken() : String?
    suspend fun clearToken()
}