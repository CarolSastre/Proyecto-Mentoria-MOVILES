package com.example.mentoria.features.auth.presentation.login

sealed interface LoginUiAction {
    data class OnLoginClick(val dni: String, val password: String) : LoginUiAction
    object OnRegisterClick: LoginUiAction
}