package com.example.mentoria.core.datastore.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mentoria.core.domain.model.Usuario
import java.time.LocalDateTime

@Entity(tableName = "registro_acceso")
class RegistroAccesoEntity (
    @PrimaryKey val id: String,
    val fechaHora: LocalDateTime,
    val accesoPermitido: Boolean,
    val mensaje: String?,
    val usuario: Usuario
)