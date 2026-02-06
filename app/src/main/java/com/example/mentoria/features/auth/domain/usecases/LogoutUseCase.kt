package com.example.mentoria.features.auth.domain.usecases

import com.example.mentoria.core.presentation.SessionManager
import com.example.mentoria.features.auth.domain.repository.AuthRepository

/**
 * No devuelve nada
 * No sabe cómo se hace logout
 * Solo ejecuta la regla de negocio
 */
class LogoutUseCase(
    private val repository: AuthRepository,
    private val sessionManager: SessionManager
) {
    suspend operator fun invoke() {
        repository.logout()
        sessionManager.notifyLoggedOut()
    }
}
