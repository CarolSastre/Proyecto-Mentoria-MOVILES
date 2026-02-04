package com.example.mentoria.core.domain.repositories

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveToken(token: String)
    fun getToken(): Flow<String?>
}