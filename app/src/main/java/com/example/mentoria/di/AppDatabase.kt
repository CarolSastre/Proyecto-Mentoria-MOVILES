package com.example.mentoria.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mentoria.core.datastore.dao.UsuarioDao
import com.example.mentoria.core.datastore.entities.UsuarioEntity

@Database(entities = [UsuarioEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
}