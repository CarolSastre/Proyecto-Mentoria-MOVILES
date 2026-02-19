package com.example.mentoria.features.auth.presentation.register

import com.example.mentoria.core.data.remote.dto.UsuarioDto

sealed interface RegisterUiAction {
    data class OnRegisterClick(
        val usuarioDto: UsuarioDto,
        val passwordConfirmation: String
    ) : RegisterUiAction
}