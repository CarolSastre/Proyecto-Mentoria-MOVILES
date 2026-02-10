package com.example.mentoria.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
object HomeKey : NavKey

@Serializable
object SearchKey : NavKey

@Serializable
object LoginKey : NavKey

@Serializable
object RegisterKey : NavKey

@Serializable
object CalendarKey: NavKey

@Serializable
object HorarioKey: NavKey

val LocalOnNavigationBack = compositionLocalOf<() -> Unit> { {} }

// data class DetallesKey(val id: String) : NavKey --------->> MIRAR DI>koin>list_navigation_viewmodel
