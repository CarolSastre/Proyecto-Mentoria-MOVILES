package com.example.mentoria.core.datastore

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class Converters {

    // --- LocalDate ---
    @TypeConverter
    fun fromLocalDate(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it) }
    }

    @TypeConverter
    fun toLocalDate(date: LocalDate?): String? {
        return date?.toString()
    }

    // --- LocalDateTime (PARA REGISTRO DE ACCESO) ---
    @TypeConverter
    fun fromLocalDateTime(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it) }
    }

    @TypeConverter
    fun toLocalDateTime(dateTime: LocalDateTime?): String? {
        return dateTime?.toString()
    }

    // --- LocalTime (PARA HORARIOS) ---
    @TypeConverter
    fun fromLocalTime(value: String?): LocalTime? {
        return value?.let { LocalTime.parse(it) }
    }

    @TypeConverter
    fun toLocalTime(time: LocalTime?): String? {
        return time?.toString()
    }
}