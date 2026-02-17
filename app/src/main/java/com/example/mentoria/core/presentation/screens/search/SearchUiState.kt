package com.example.mentoria.core.presentation.screens.search

import com.example.mentoria.core.domain.model.Departamento
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import java.time.LocalDate

data class SearchUiState(
    val usuario: Usuario = Usuario(
        id = "69935128cd34aa5b7685a3f4",
        dni = "10000000P",
        nombre="Profesor1",
        apellidos="Docente1",
        nfc = "NFC_PROFE_1",
        rol = Rol.PROFESOR,
        fechaNacimiento = LocalDate.parse("1979-12-31"),
        gmail = "profe1@instituto.com",
        departamento = Departamento(
            id = "69935128cd34aa5b7685a3f0",
            nombre = "Informática"
        ),
        baja = false,
        curso = null,
    ),
    val isLoading: Boolean = false,
    val expanded: Boolean = false,
    val query: String = "",
    val usuarios: List<Usuario> = listOf(
        Usuario(
            id = "69935128cd34aa5b7685a3f4",
            dni = "10000000P",
            nombre="Profesor1",
            apellidos="Docente1",
            nfc = "NFC_PROFE_1",
            rol = Rol.PROFESOR,
            fechaNacimiento = LocalDate.parse("1979-12-31"),
            gmail = "profe1@instituto.com",
            departamento = Departamento(
                id = "69935128cd34aa5b7685a3f0",
                nombre = "Informática"
            ),
            baja = false,
            curso = null,
        )
    ),
)
