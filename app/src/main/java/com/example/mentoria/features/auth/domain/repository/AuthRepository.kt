package com.example.mentoria.features.auth.domain.repository

import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario

interface AuthRepository {
    suspend fun login(dni: String, password: String): Usuario
    suspend fun register(
        dni: String,
        password: String,
        nombre: String,
        apellidos: String,
        email:String,
        fechaNacimiento: String,
        rol: Rol
    ): Usuario // al registrar a alguien siempre es un alumno
    suspend fun logout()
    suspend fun isUserLoggedIn(): Boolean
}