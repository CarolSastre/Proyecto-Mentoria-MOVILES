package com.example.mentoria.features.auth.data.remote

import com.example.mentoria.core.data.remote.dto.UsuarioDto
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.features.auth.data.remote.dto.LoginRequest

class FakeUsuariosApiService: AuthApi {
    /*
    override suspend fun getUsuarios(
    ): List<UsuarioDto> {
        return listOf(
            UsuarioDto(
                id = "1",
                dni = "12345678A",
                nombre = "Carolina",
                apellidos = "Sastre Garrido",
                password = "carol1",
                rol = Rol.ADMIN.toString(),
                nfc = "",
                fechaNacimiento = "2001-01-01",
                gmail = "carolsaga02@gmail.com",
                departamento = null,
                baja = false,
                curso = null
            )
        )
    }
     */

    override suspend fun login(request: LoginRequest): UsuarioDto {
        return UsuarioDto(
            dni = "123456789A",
            nfc = "1234567890123456",
            password = "profesor",
            rol = "PROFESOR",
            nombre = "Profesor",
            apellidos = "Prueba"
        )
    }
}