package com.example.mentoria.core.datastore

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mentoria.core.datastore.dao.UsuarioDao
import com.example.mentoria.core.datastore.entities.DepartamentoEntity
import com.example.mentoria.core.datastore.entities.HorarioEntity
import com.example.mentoria.core.datastore.entities.RegistroAccesoEntity
import com.example.mentoria.core.datastore.entities.UsuarioEntity

@Database(
    entities = [
        UsuarioEntity::class,
        DepartamentoEntity::class,
        HorarioEntity::class,
        RegistroAccesoEntity::class
    ],
    version = 1,
    exportSchema = false // <--- Esto limpia el log de warnings
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
}