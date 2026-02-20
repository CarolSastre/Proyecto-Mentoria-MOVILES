package com.example.mentoria.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.mentoria.core.data.local.entities.UsuarioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM usuarios")
    fun getUsuarios(): Flow<List<UsuarioEntity>>
    @Query("SELECT * FROM usuarios WHERE id = :id")
    fun getUsuarioById(id: String): Flow<UsuarioEntity>

    // CAMBIO: Devuelve List<Long> en lugar de Unit para evitar error "jvm signature V"
    @Insert(onConflict = REPLACE)
    suspend fun insertAll(usuarios: List<UsuarioEntity>): List<Long>
    @Insert(onConflict = REPLACE)
    suspend fun insert(usuario: UsuarioEntity): Long

    // CAMBIO: Devuelve Int (filas afectadas) en lugar de Unit
    @Query("DELETE FROM usuarios")
    suspend fun deleteAll(): Int
}