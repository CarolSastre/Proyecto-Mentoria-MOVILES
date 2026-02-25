package com.example.mentoria.core.data.remote

import com.example.mentoria.core.data.remote.dto.FaltaRequest
import com.example.mentoria.core.data.remote.dto.RegistroAccesoDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RegistroApiService {
    @GET("/api/asistencia/alumno/{id}")
    fun getRegistrosFromAlumno(@Path("id") usuId: String): Flow<List<RegistroAccesoDto>>
    @POST("/api/asistencia/falta")
    suspend fun setFalta(@Body faltaDto: FaltaRequest): Response<String>
}