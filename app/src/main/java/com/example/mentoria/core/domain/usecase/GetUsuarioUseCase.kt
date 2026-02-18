package com.example.mentoria.core.domain.usecase

import com.example.mentoria.core.data.repositories.UsuarioRepositoryRemoteImpl
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.domain.repositories.UsuarioRepository
import kotlinx.coroutines.flow.Flow

class GetUsuarioUseCase(
    private val usuarioRepository: UsuarioRepositoryRemoteImpl
) {
    operator fun invoke(id: String): Flow<Usuario> {
        return usuarioRepository.getUsuarioById(id)
    }
}