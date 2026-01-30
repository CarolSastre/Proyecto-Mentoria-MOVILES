package com.example.mentoria.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.mentoria.core.presentation.ObserveAsEvents
import com.example.mentoria.core.presentation.SessionEvent
import com.example.mentoria.core.presentation.SessionManager
import com.example.mentoria.features.auth.presentation.login.LoginRoute
import com.example.mentoria.features.auth.presentation.register.RegisterRoute
import com.example.mentoria.main.presentation.home.HomeScreen
import org.koin.compose.koinInject

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    startDestination: NavKey = LoginKey,
    sessionManager: SessionManager = koinInject()
) {
    // Navegación global con Navigation 3
    val backStack = rememberNavBackStack(startDestination)

    sessionManager.events.ObserveAsEvents { event ->
        when (event) {
            SessionEvent.LoggedOut -> {
                backStack.clear()
                backStack.add(LoginKey)
            }
        }
    }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { route ->
            when (route) {
                is LoginKey -> NavEntry(route) {
                    LoginRoute(
                        onLoginSuccess = {
                            backStack.add(HomeKey)
                        },
                        onNavigateToRegister = {
                            backStack.add(RegisterKey)
                        }
                    )
                }

                is RegisterKey -> NavEntry(route) {
                    RegisterRoute(
                        onRegisterSuccess = {
                            backStack.add(HomeKey)
                        },
                        onBack = { backStack.removeLastOrNull() }
                    )
                }

                is HomeKey -> NavEntry(route) {
                    HomeScreen(
                        onSearchClick = { /*TODO*/ },
                        onSettingsClick = { /*TODO*/ },
                        onLogOut = {
                            backStack.clear()
                            backStack.add(LoginKey)
                        }
                    )
                }

                else -> error("Ruta no reconocida: $route")
            }
        }
    )
}