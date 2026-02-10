package com.example.mentoria.core.datastore.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "registro_acceso")
data class RegistroAccesoEntity(
    @PrimaryKey val id: String,
    val fechaHora: LocalDateTime, // Requiere Converter
    val accesoPermitido: Boolean,
    val mensaje: String?,
    // ERROR ANTERIOR: val usuario: Usuario
    // SOLUCIÓN: Guardamos solo el ID (Clave foránea)
    val usuarioId: String
)