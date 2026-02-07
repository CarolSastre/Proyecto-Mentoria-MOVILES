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

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(usuarios: List<UsuarioEntity>)

    @Query("DELETE FROM usuarios")
    suspend fun deleteAll()
}