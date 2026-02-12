package com.example.mentoria.features.auth.domain.usecases

import com.example.mentoria.features.auth.domain.repository.AuthRepository

/**
 * 👉
 * El dominio solo pregunta si hay sesión
 * No sabe de tokens, DataStore, Flow, etc.
 */

class IsUserLoggedInUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): Boolean {
        //return true
        return repository.isUserLoggedIn()
    }
}
