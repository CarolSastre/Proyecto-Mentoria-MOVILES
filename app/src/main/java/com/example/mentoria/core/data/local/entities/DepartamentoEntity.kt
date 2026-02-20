package com.example.mentoria.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "departamentos")
data class DepartamentoEntity(
    @PrimaryKey val id: String,
    val nombre: String
)