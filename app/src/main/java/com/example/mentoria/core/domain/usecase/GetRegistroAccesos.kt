package com.example.mentoria.core.domain.usecase

import com.example.mentoria.core.domain.model.RegistroAcceso
import com.example.mentoria.core.domain.repositories.RegistroAccesoRepository
import kotlinx.coroutines.flow.Flow

class GetRegistrosFromUsuarioUseCase(
    private val repository: RegistroAccesoRepository
) {
    operator fun invoke(usuarioId: String): Flow<List<RegistroAcceso>> {
        return repository.getRegistrosFromUsuario(usuarioId)
    }
}