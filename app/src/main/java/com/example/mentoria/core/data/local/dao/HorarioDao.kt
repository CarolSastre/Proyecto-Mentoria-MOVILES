package com.example.mentoria.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.mentoria.core.data.local.entities.HorarioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HorarioDao {
    @Query("SELECT * FROM horarios WHERE id = :id")
    fun getHorarioById(id: String): Flow<HorarioEntity>

    @Query("SELECT * FROM horarios")
    fun getAllHorarios(): Flow<List<HorarioEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(registros: List<HorarioEntity>)

    @Query("DELETE FROM horarios WHERE id = :id")
    suspend fun deleteHorario(id: String)

    @Query("DELETE FROM horarios")
    suspend fun deleteAll()
}