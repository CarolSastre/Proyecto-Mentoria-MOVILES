package com.example.mentoria.core.data.remote

import com.example.mentoria.core.data.remote.dto.RegistroAccesoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface RegistroApiService {
    companion object {
        const val DEFAULT_REGION = "ES"
        const val API_HOST = "" // "api.themoviedb.org"
        const val API_VERSION_PATH = "3"
        //const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    // TODO: aquí hay que definir (y modificar) los endpoints
    @GET("/registros")
    suspend fun getRegistros(): List<RegistroAccesoDto>
}