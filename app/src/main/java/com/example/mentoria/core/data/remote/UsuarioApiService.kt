package com.example.mentoria.core.data.remote

import com.example.mentoria.core.data.remote.dto.UsuarioDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UsuarioApiService {

    // TODO: aquí hay que definir (y modificar) los endpoints para usuarios
    @GET("/api/usuarios")
    suspend fun getAllUsuarios(): List<UsuarioDto>

    @GET("/api/usuarios/{id}")
    suspend fun getUsuarioById(@Path("id") id: String): UsuarioDto

//    @GET("/usuarios/{query}")
//    suspend fun searchUsuario(@Path("query") query: String): UsuarioResponse

    //@POST("usuario")
    //suspend fun createUsuario(@Body usuario: RegisterUsuarioRequest): UsuarioDto
}