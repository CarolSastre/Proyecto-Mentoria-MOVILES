package com.example.mentoria.core.data.remote

import com.example.mentoria.features.auth.data.remote.dto.RegisterUsuarioRequest
import com.example.mentoria.core.data.remote.dto.UsuarioDto
import com.example.mentoria.core.data.remote.dto.UsuarioResponse

class MentoriaApiServiceImpl(
    // TODO: en este ejemplo rafa utiliza ktor, pero habría que usar retrofit?
    //private val client: HttpClient
) : MentoriaApiService {
    override suspend fun fetchUsuarios(
        page: Int,
        region: String?
    ): List<UsuarioDto> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchProfesores(
        page: Int,
        region: String?
    ): List<UsuarioDto> {
        TODO("Not yet implemented")
    }

    override suspend fun searchUsuario(
        query: String,
        page: Int
    ): UsuarioResponse {
        TODO("Not yet implemented")
    }

    override suspend fun createUsuario(usuario: RegisterUsuarioRequest): UsuarioDto {
        TODO("Not yet implemented")
    }

    companion object Factory {
        fun create(): MentoriaApiService {
            return MentoriaApiServiceImpl(
                // de Gemini
                //client = HttpClient(Android)
                // de rafa (con ktor)
                // client = KtorClientProvider.client
            )
        }
    }
}