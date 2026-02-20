package com.example.mentoria.core.data.remote

import com.example.mentoria.core.data.MockDataProvider
import com.example.mentoria.core.data.remote.dto.UsuarioDto
import com.example.mentoria.core.data.remote.mappers.toDto
import com.example.mentoria.core.domain.model.Rol

class FakeUsuariosApiService : UsuarioApiService {
    override suspend fun getAllUsuarios(): List<UsuarioDto> {
        return MockDataProvider.usuarios.map { it.toDto() }
    }

    override suspend fun getUsuarioById(id: String): UsuarioDto {
        return MockDataProvider.usuarios.find { it.id == id }!!.toDto()
    }
}