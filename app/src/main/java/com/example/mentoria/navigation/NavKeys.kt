package com.example.mentoria.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
object HomeKey : NavKey

@Serializable
object LoginKey : NavKey

@Serializable
object RegisterKey : NavKey

@Serializable
object CalendarKey: NavKey

@Serializable
object HorarioKey: NavKey

@Serializable
data class UsuarioDetailsKey(val id: String) : NavKey // --------->> MIRAR DI>koin>list_navigation_viewmodel
