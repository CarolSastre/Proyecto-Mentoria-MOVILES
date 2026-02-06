package com.example.mentoria.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Cada destino es un NavKey serializable que representa una pantalla
 */

@Serializable
object HomeKey : NavKey

@Serializable
object SearchKey : NavKey

@Serializable
object LoginKey : NavKey

@Serializable
object RegisterKey : NavKey

val LocalOnNavigationBack = compositionLocalOf<() -> Unit> { {} }

// data class DetallesKey(val id: String) : NavKey
