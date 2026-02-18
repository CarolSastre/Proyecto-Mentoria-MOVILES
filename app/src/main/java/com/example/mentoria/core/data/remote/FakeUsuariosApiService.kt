package com.example.mentoria.core.data.remote

import com.example.mentoria.core.data.remote.dto.UsuarioDto
import com.example.mentoria.core.domain.model.Rol
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeUsuariosApiService : UsuarioApiService {
    override suspend fun getAllUsuarios(): List<UsuarioDto> {
        return listOf(
            UsuarioDto(
                id = "1",
                dni = "12345678A",
                nombre = "Carolina",
                apellidos = "Sastre Garrido",
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

    override suspend fun getUsuarioById(id: String): UsuarioDto {
        return UsuarioDto(
            id = "1",
            dni = "12345678A",
            nombre = "Carolina",
            apellidos = "Sastre Garrido",
            rol = Rol.ADMIN.toString(),
            nfc = "",
            fechaNacimiento = "2001-01-01",
            gmail = "carolsaga02@gmail.com",
            departamento = null,
            baja = false,
            curso = null
        )
    }
}