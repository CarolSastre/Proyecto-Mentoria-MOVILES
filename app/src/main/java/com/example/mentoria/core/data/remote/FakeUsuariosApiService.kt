package com.example.mentoria.core.data.remote

import com.example.mentoria.core.data.remote.dto.UsuarioDto
import com.example.mentoria.core.domain.model.Rol
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeUsuariosApiService : UsuarioApiService {
    override fun getAllUsuarios(): Flow<List<UsuarioDto>> {
        return flowOf(
            listOf(
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
        )
    }

    override fun getUsuarioById(id: String): Flow<UsuarioDto> {
        return flowOf(
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
}