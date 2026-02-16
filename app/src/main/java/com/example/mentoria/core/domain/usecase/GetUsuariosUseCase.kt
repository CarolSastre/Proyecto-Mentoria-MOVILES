package com.example.mentoria.core.domain.usecase

import com.example.mentoria.core.domain.repositories.UsuarioRepository

class GetUsuariosUseCase(
    private val repository: UsuarioRepository
) {
    operator fun invoke() = repository.getAllUsuarios()
}