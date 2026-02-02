package com.example.mentoria.features.auth.data.mapper

import com.example.mentoria.features.auth.data.remote.dto.LoginResponse
import com.example.mentoria.core.domain.model.Usuario

fun LoginResponse.toDomain() = Usuario(
    dni = dni,
    password = password,
    token = token
)

fun Register.toDomain() = Usuario(
    dni = dni,
    password,
    token = this.token
)