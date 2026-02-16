package com.example.mentoria.core.data.remote

import com.example.mentoria.core.data.remote.dto.UsuarioDto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface UsuarioApiService {
    companion object {
        const val DEFAULT_REGION = "ES"
        const val API_HOST = "" // "api.themoviedb.org"
        const val API_VERSION_PATH = "3"
        //const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    // TODO: aquí hay que definir (y modificar) los endpoints
    @GET("/usuarios")
    fun getAllUsuarios(): Flow<List<UsuarioDto>>

    @GET("/usuarios/{id}")
    fun getUsuarioById(@Path("id") id: String): Flow<UsuarioDto>

//    @GET("/usuarios/{query}")
//    suspend fun searchUsuario(@Path("query") query: String): UsuarioResponse

    //@POST("usuario")
    //suspend fun createUsuario(@Body usuario: RegisterUsuarioRequest): UsuarioDto
}