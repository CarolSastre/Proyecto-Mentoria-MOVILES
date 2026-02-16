package com.example.mentoria.core.presentation.screens.home

import com.example.mentoria.core.domain.model.RegistroAcceso
import com.example.mentoria.core.domain.model.Usuario

data class HomeUiState (
    val usuario: Usuario? = null,
    val registros: List<RegistroAcceso> = emptyList()
)