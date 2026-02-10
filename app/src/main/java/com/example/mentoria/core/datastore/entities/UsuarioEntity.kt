package com.example.mentoria.core.datastore.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mentoria.core.domain.model.Rol
import java.time.LocalDate

@Entity(tableName = "usuarios")
data class UsuarioEntity(
    @PrimaryKey val id: String,
    val dni: String,
    val nombre: String,
    val apellidos: String,
    val nfc: String,
    val password: String?, // Puede ser nulo si viene de la API sin pass
    val rol: Rol,
    val fechaNacimiento: LocalDate?,
    val gmail: String,
    val baja: Boolean,
    val curso: String,
    val fotoPerfil: String?, // <--- Asegúrate de tener esto para la foto

    // El warning sale aquí. Es normal porque DepartamentoEntity tiene su propia @PrimaryKey.
    // Al usar @Embedded, esa PrimaryKey se ignora en esta tabla.
    @Embedded(prefix = "dep_")
    val departamento: DepartamentoEntity?
)
