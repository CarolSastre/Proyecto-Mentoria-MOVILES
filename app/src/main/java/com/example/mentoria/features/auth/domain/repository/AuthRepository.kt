package com.example.mentoria.features.auth.domain.repository

<<<<<<< HEAD
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
=======
import com.example.mentoria.core.domain.model.Usuario

interface AuthRepository {
    suspend fun login(email: String, password: String): Usuario?
    suspend fun register(email: String, password: String): Usuario
>>>>>>> origin/modificaciones
    suspend fun logout()
    suspend fun isUserLoggedIn(): Boolean
}