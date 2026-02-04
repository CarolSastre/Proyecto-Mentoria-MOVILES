package com.example.mentoria.core.data.remote

import com.example.mentoria.core.data.remote.dto.RegistroAccesoDto
import com.example.mentoria.core.data.remote.dto.UsuarioDto

class FakeRegistrosApiService: RegistroApiService {
    override suspend fun getRegistros(): List<RegistroAccesoDto> {
        return listOf(
            RegistroAccesoDto(
                id = "1",
                fechaHora = "2025-06-15",
                accesoPermitido = true,
                mensaje = "Acceso permitido",
                usuario = UsuarioDto(
                    dni = "12345678A",
                    password = "passw0rd",
                    nombre = "Carolina",
                    apellidos = "Sastre Garrido",
                    rol = "ADMIN",
                    nfc = ""
                )
            ),
            RegistroAccesoDto(
                id = "2",
                fechaHora = "2025-06-16",
                accesoPermitido = true,
                mensaje = "Acceso permitido",
                usuario = UsuarioDto(
                    dni = "12345678A",
                    password = "passw0rd",
                    nombre = "Carolina",
                    apellidos = "Sastre Garrido",
                    rol = "ADMIN",
                    nfc = ""
                )
            )
        )
    }
}