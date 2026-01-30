package com.example.mentoria.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Cada destino es un NavKey serializable que representa una pantalla
 */

@Serializable
object HomeKey : NavKey

@Serializable
object LoginKey : NavKey

@Serializable
object RegisterKey : NavKey