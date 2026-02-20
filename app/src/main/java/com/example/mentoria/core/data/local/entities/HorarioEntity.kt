package com.example.mentoria.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalTime

@Entity(tableName = "horarios")
data class HorarioEntity(
    @PrimaryKey val id: String,
    val diaSemana: String,
    val horaInicio: LocalTime, // Requiere Converter
    val horaFin: LocalTime,    // Requiere Converter
    val departamentoId: String? // Guardamos ID, no el objeto Departamento
)