package com.example.mentoria.core.data.remote

import com.example.mentoria.core.data.remote.dto.UsuarioDto
import com.example.mentoria.core.domain.model.Rol

class FakeUsuariosApiService: MentoriaApiService {
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
}