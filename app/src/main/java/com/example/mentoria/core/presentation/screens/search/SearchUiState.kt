package com.example.mentoria.core.presentation.screens.search

import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario

data class SearchUiState(
    val usuario: Usuario = Usuario(
        id = "0",
        dni = "0",
        nombre ="Usuario",
        apellidos = "Anónimo",
        gmail = "ejemplo@gamil.com",
        rol = Rol.ALUMNO,
        fechaNacimiento = null,
        curso = null,
        departamento = null,
        baja = false,
        nfc = null,
        fotoPerfilUrl = null,
    ),
    val isLoading: Boolean = false,
    val expanded: Boolean = false,
    val query: String = "",
    val usuarios: List<Usuario> = emptyList(),
)
