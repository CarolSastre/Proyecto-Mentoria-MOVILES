package com.example.mentoria.features.auth.data.mapper

import com.example.mentoria.features.auth.domain.model.Usuario

fun LoginResponse.toDomain() = Usuario(
    id = this.id,
    nombre = this.nombre,
    correo = this.correo,
    token = this.token
)

fun Register.toDomain() = Usuario