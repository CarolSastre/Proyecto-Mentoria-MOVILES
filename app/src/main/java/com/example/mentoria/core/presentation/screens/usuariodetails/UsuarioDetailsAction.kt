package com.example.mentoria.core.presentation.screens.usuariodetails

sealed interface UsuarioDetailsAction {
    object OnEditClick : UsuarioDetailsAction
    object OnDeleteClick : UsuarioDetailsAction
}