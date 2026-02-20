package com.example.mentoria.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.mentoria.core.data.local.entities.RegistroAccesoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RegistroDao {
    @Query("SELECT * FROM registro_acceso")
    fun getAllRegistros(): Flow<List<RegistroAccesoEntity>>

    @Query("SELECT * FROM registro_acceso WHERE usuarioId = :usuarioId")
    fun getAllRegistrosFromUsuario(usuarioId: String): Flow<List<RegistroAccesoEntity>>

    @Query("SELECT * FROM registro_acceso WHERE id = :registroId")
    fun getRegistro(registroId: String): Flow<RegistroAccesoEntity>

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(registros: List<RegistroAccesoEntity>)

    @Query("DELETE FROM registro_acceso WHERE id = :registroId")
    suspend fun deleteRegistro(registroId: String)

    @Query("DELETE FROM registro_acceso")
    suspend fun deleteAll()
}
