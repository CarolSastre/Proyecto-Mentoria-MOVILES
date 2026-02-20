package com.example.mentoria.features.auth.presentation.login

data class LoginUiState(
    val isLoading: Boolean = false,
    val error: String? = null
)