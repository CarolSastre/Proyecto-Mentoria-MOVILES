package com.example.mentoria.features.auth.presentation.register


sealed interface RegisterUiEvent {
    object RegisterSuccess: RegisterUiEvent
    object Error: RegisterUiEvent
}