package com.example.mentoria.features.auth.data.local

import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {
    suspend fun saveToken(token: String)

    fun getToken(): Flow<String>

    suspend fun clear()
}