package com.example.mentoria.core.presentation.screens.crearUsuario


sealed interface CrearUsuarioUiEvent {
    object RegisterSuccess: CrearUsuarioUiEvent
    object Error: CrearUsuarioUiEvent
}