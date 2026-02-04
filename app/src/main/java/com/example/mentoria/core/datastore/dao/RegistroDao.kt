package com.example.mentoria.core.datastore.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.mentoria.core.datastore.entities.RegistroEntity

@Dao
interface RegistroDao {
    @Query("SELECT * FROM registro_accesos")
    suspend fun getUsuarios(): List<RegistroEntity>

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(registro: List<RegistroEntity>)

    @Query("DELETE FROM registro_accesos")
    suspend fun deleteAll()
}