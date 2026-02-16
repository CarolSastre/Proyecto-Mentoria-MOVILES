package com.example.mentoria.core.domain.usecase

import com.example.mentoria.core.domain.model.RegistroAcceso
import com.example.mentoria.core.domain.repositories.RegistroAccesoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetRegistrosFromUsuarioUseCase(
    private val repository: RegistroAccesoRepository
) {
    operator fun invoke(usuarioId: String): Flow<List<RegistroAcceso>> {
        //return repository.login(LoginRequest(dni, password)).usuario?.toDomain()
        return flowOf(emptyList())
    }
}