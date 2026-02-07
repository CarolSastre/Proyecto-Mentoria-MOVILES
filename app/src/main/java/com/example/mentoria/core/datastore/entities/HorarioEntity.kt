package com.example.mentoria.core.datastore.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalTime

@Entity(tableName = "horarios")
class HorarioEntity (
    @PrimaryKey val id: String,
    val curso: String,
    val diaSemana: String,
    val horaInicio: LocalTime,
    val horaFin: LocalTime
)