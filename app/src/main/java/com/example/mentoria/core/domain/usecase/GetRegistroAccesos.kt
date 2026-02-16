package com.example.mentoria.core.domain.usecase

import com.example.mentoria.core.domain.model.RegistroAcceso
import com.example.mentoria.core.domain.repositories.RegistroAccesoRepository
import kotlinx.coroutines.flow.Flow

class GetRegistroFromUsuarioUseCase(
    private val repository: RegistroAccesoRepository
) {
    operator fun invoke(usuarioId: String): Flow<List<RegistroAcceso>> {
        //return authRepository.login(LoginRequest(dni, password)).usuario?.toDomain()
        TODO("Not yet implemented")
    }
}