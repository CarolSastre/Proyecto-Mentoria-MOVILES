package com.example.mentoria.core.data.remote

import androidx.room.Room
import com.example.mentoria.core.data.remote.dto.UsuarioDto
import com.example.mentoria.core.data.remote.dto.UsuarioResponse
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.di.AppDatabase
import okhttp3.internal.platform.PlatformRegistry.applicationContext

class FakeUsuariosApiService: UsuarioApiService {
    override suspend fun getUsuarios(
    ): List<UsuarioDto> {
        /*
        val db = Room.databaseBuilder(
            applicationContext!!,
            AppDatabase::class.java, "usuarios-database"
        ).build()
         */

        return listOf(
            UsuarioDto(
                dni = "12345678A",
                password = "passw0rd",
                nombre = "Carolina",
                apellidos = "Sastre Garrido",
                rol = "ADMIN",
                nfc = ""
            ),
            UsuarioDto(
                dni ="12345678B",
                nombre = "Manuela",
                apellidos = "Carmela",
                rol = "PROFESOR",
                password = "passw0rd",
                nfc = null
            )
        )
    }

    override suspend fun searchUsuario(
        query: String,
    ): UsuarioResponse {
        TODO("Not yet implemented")
    }
}