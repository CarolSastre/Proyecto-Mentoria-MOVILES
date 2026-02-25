package com.example.mentoria.core.presentation.screens.home

import com.example.mentoria.core.domain.model.RegistroAcceso
import com.example.mentoria.core.domain.model.Usuario

data class HomeUiState (
    val usuario: Usuario? = null,
    val query: String = "",
    val expanded: Boolean = false,
    val registros: List<RegistroAcceso> = emptyList(),
    val usuarios: List<Usuario> = emptyList(),
)