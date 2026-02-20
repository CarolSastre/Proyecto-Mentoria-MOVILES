package com.example.mentoria.core.presentation.screens.crearUsuario

import com.example.mentoria.core.data.remote.dto.UsuarioDto

sealed interface CrearUsuarioUiAction {
    data class OnRegisterClick(
        val usuarioDto: UsuarioDto,
        val passwordConfirmation: String
    ) : CrearUsuarioUiAction
}