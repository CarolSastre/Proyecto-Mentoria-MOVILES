package com.example.mentoria.core.data.remote

import com.example.mentoria.features.auth.data.remote.dto.RegisterUsuarioRequest
import com.example.mentoria.core.data.remote.dto.UsuarioDto
import com.example.mentoria.core.data.remote.dto.UsuarioResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MentoriaApiService {
    companion object {
        const val DEFAULT_REGION = "ES"
        const val API_HOST = "" // "api.themoviedb.org"
        const val API_VERSION_PATH = "3"
        //const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    // TODO: aquí hay que definir (y modificar) los endpoints, los puestos aquí son completamente provisionales
    @GET("/getUsuarios")
    suspend fun fetchUsuarios(
        page: Int = 1,
        region: String? = DEFAULT_REGION): List<UsuarioDto>

    @GET("/profesores")
    suspend fun fetchProfesores( // ?
        page: Int = 1,
        region: String? = DEFAULT_REGION): List<UsuarioDto>
    @GET("/usuarios/{query}")
    suspend fun searchUsuario(
        query: String,
        page: Int = 1): UsuarioResponse

    @POST("registro")
    suspend fun createUsuario(@Body usuario: RegisterUsuarioRequest): UsuarioDto


}