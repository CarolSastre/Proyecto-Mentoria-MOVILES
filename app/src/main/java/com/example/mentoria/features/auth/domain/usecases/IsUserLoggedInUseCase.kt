package com.example.mentoria.features.auth.domain.usecases

import com.example.mentoria.features.auth.domain.repository.AuthRepository

class IsUserLoggedInUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): Boolean {
        //return true
        return repository.currentUser.value != null
    }
}
