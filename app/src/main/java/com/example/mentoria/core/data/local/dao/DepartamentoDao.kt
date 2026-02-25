package com.example.mentoria.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.mentoria.core.data.local.entities.DepartamentoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DepartamentoDao {
    @Query("SELECT * FROM departamentos WHERE id = :id")
    fun getDepartamentoById(id: String): Flow<DepartamentoEntity>

    @Query("SELECT * FROM departamentos WHERE nombre LIKE :nombre")
    fun getDepartamentoByNombre(nombre: String): Flow<DepartamentoEntity>

    @Query("SELECT * FROM departamentos")
    fun getAllDepartamento(): Flow<List<DepartamentoEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(registros: List<DepartamentoEntity>)

    @Query("DELETE FROM departamentos")
    suspend fun deleteAll()
}