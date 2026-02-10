package com.example.mentoria.core.datastore.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.mentoria.core.datastore.entities.UsuarioEntity

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM usuarios")
    suspend fun getUsuarios(): List<UsuarioEntity>

    // CAMBIO: Devuelve List<Long> en lugar de Unit para evitar error "jvm signature V"
    @Insert(onConflict = REPLACE)
    suspend fun insertAll(usuarios: List<UsuarioEntity>): List<Long>

    // CAMBIO: Devuelve Int (filas afectadas) en lugar de Unit
    @Query("DELETE FROM usuarios")
    suspend fun deleteAll(): Int
}