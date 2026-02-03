package com.example.mentoria.core.datastore.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mentoria.core.domain.model.Rol

@Entity(tableName = "usuarios")
data class UsuarioEntity(
    @PrimaryKey val dni: String,
    val password: String,
    val nombre: String,
    val apellidos: String,
    val rol: Rol,
    val nfc: String?
)
