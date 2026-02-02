package com.example.mentoria.core.data.repositories

import com.example.mentoria.core.data.remote.MentoriaApiService
import com.example.mentoria.core.data.remote.mappers.toDomain
import com.example.mentoria.core.domain.model.Usuario

class UsuariosRepositoryRemoteImpl(
    private val api: MentoriaApiService
) {
    suspend fun fetchUsuarios(page: Int): List<Usuario> {
        TODO("Not yet implemented")
        //return api.fetchUsuarios(page)
//            .map { usuarioDto -> usuarioDto.toDomain() }
    }

    suspend fun fetchProfesores(page: Int): List<Usuario> {
        TODO("Not yet implemented")
    }

    suspend fun fetchUsuarios(
        query: String, page: Int
    ) = api.searchUsuario(query, page).results.map {
            it.toDomain()
        }

}