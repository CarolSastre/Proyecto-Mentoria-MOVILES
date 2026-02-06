package com.example.mentoria.features.auth.data.mapper

import com.example.mentoria.features.auth.data.remote.dto.LoginResponse
import com.example.mentoria.features.auth.domain.model.Usuario

fun LoginResponse.toDomain() = Usuario(
<<<<<<< HEAD
    dni = this.dni,
=======
    email = this.email,
>>>>>>> origin/modificaciones
    password = this.password,
    token = this.token
)

// fun Register.toDomain() = Usuario