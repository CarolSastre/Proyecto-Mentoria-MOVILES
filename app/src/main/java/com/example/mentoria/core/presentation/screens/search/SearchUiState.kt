package com.example.mentoria.core.presentation.screens.search

import com.example.mentoria.R
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario

data class SearchUiState(
    val usuario: Usuario = Usuario(
        id = "0",
        dni = "",
        nombre = "Usuario",
        apellidos = "Anónimo",
        rol = Rol.ALUMNO,
        gmail = "",
        baja = false,
        fechaNacimiento = null,
        curso = null,
        departamento = null,
        nfc = null,
        fotoPerfilUrl = R.drawable.ic_error_imagen.toString(),
    ),
    val query: String = "",
    val expanded: Boolean = false,
    val isLoading: Boolean = false,
    val usuarios: List<Usuario> = emptyList()
)
