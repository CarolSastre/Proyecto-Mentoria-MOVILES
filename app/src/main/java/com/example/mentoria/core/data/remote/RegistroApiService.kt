package com.example.mentoria.core.data.remote

import com.example.mentoria.core.data.remote.dto.RegistroAccesoDto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface RegistroApiService {
    @GET("/api/registros")
    fun getAllRegistros(): Flow<List<RegistroAccesoDto>>
    @GET("/api/registros/{id}")
    suspend fun getRegistroById(@Path("id") id: String): RegistroAccesoDto
}