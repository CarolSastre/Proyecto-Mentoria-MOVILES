package com.example.mentoria.core.domain.usecase

import com.example.mentoria.core.domain.repositories.UsuarioRepository

class GetAllUsuariosUseCase(
    private val repository: UsuarioRepository
) {
    operator fun invoke() = repository.getAllUsuarios()
}