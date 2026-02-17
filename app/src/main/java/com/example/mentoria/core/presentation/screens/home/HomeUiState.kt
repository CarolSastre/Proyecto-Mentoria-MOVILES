package com.example.mentoria.core.presentation.screens.home

import com.example.mentoria.core.domain.model.Departamento
import com.example.mentoria.core.domain.model.RegistroAcceso
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import java.time.LocalDate

data class HomeUiState (
    val usuario: Usuario? = null,
    val registros: List<RegistroAcceso> = emptyList()
)