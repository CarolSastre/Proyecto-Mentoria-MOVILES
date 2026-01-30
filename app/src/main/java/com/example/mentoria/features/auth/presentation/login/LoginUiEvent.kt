package com.example.mentoria.features.auth.presentation.login

sealed interface LoginUiEvent {
    object LoginSuccess : LoginUiEvent
}