package com.example.mentoria.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mentoria.core.data.local.dao.UsuarioDao
import com.example.mentoria.core.data.local.Converters
import com.example.mentoria.core.data.local.entities.DepartamentoEntity
import com.example.mentoria.core.data.local.entities.RegistroAccesoEntity
import com.example.mentoria.core.data.local.entities.UsuarioEntity
import com.example.mentoria.core.data.local.entities.HorarioEntity

@Database(
    entities = [
        UsuarioEntity::class,
        DepartamentoEntity::class,
        HorarioEntity::class,
        RegistroAccesoEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
}