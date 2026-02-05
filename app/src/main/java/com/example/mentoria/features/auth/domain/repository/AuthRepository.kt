package com.example.mentoria.features.auth.domain.repository

import com.example.mentoria.features.auth.domain.model.Usuario

interface AuthRepository {
    suspend fun login(dni: String, password: String): Usuario
    suspend fun register(
        dni: String,
        nombre: String,
        apellidos: String,
        password: String,
        fechaNacimiento: String,
        gmail: String
    ): Usuario
    suspend fun logout()
    suspend fun isUserLoggedIn(): Boolean
}