package com.example.mentoria.core.data.remote

import com.example.mentoria.core.data.remote.dto.UsuarioDto
import com.example.mentoria.core.data.remote.dto.UsuarioResponse

class FakeUsuariosApiService: MentoriaApiService {
    override suspend fun getUsuarios(
    ): List<UsuarioDto> {
        return listOf(
            UsuarioDto(
                dni = "12345678A",
                password = "passw0rd",
                nombre = "Carolina",
                apellidos = "Sastre Garrido",
                rol = "ADMIN",
                nfc = ""
            )
        )
    }

    override suspend fun searchUsuario(
        query: String,
    ): UsuarioResponse {
        TODO("Not yet implemented")
    }
}