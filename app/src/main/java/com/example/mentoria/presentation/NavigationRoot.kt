package com.example.prexamen.ui

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.mentoria.presentation.screens.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
data object HomeScreenKey : NavKey

@Serializable
data object LoginScreenKey : NavKey

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(
        LoginScreenKey
    )

    // Muestra la info, necesita el backstack y el provider
    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        entryProvider = entryProvider {
            entry<LoginScreenKey> {
                // TODO: pasarle el user y password desde aquí para validar la entrada ?
                LoginScreen()
            }
            entry<HomeScreenKey> {
                HomeScreen() {
                    backStack.removeLastOrNull()
                }
            }
        }
    )
}