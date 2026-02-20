package com.example.mentoria.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.mentoria.core.data.local.entities.HorarioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DepartamentoDao {
    @Query("SELECT * FROM departamentos WHERE id = :id")
    fun getDepartamentoById(id: String): Flow<HorarioEntity>

    @Query("SELECT * FROM departamentos WHERE nombre LIKE :nombre")
    fun getDepartamentoByNombre(nombre: String): Flow<HorarioEntity>

    @Query("SELECT * FROM departamentos")
    fun getAllDepartamento(): Flow<List<HorarioEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(registros: List<HorarioEntity>)

    @Query("DELETE FROM departamentos")
    suspend fun deleteAll()
}