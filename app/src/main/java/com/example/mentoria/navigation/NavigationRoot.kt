package com.example.mentoria.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.mentoria.core.domain.model.RegistroAcceso
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.presentation.screens.SearchScreen
import com.example.mentoria.core.presentation.screens.home.HomeRoute
//import com.example.mentoria.features.auth.presentation.login.LoginRoute
//import com.example.mentoria.features.auth.presentation.register.RegisterRoute
import com.example.mentoria.core.presentation.screens.home.HomeScreen
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    startDestination: NavKey = HomeKey, // LoginKey
    //sessionManager: SessionManager = koinInject()
) {
    // Navegación global con Navigation 3
    val backStack = rememberNavBackStack(startDestination)
/*
    sessionManager.events.ObserveAsEvents { event ->
        when (event) {
            SessionEvent.LoggedOut -> {
                backStack.clear()
                backStack.add(LoginKey)
            }
        }
    }
 */
    // TODO: quitar todo esto de prueba
    val usuarios = listOf(
        Usuario(
            dni ="12345678A",
            nombre = "Carolina",
            apellidos = "Sastre Garrido",
            rol = "ADMIN",
            password = "passw0rd",
            nfc = null),
        Usuario(
            dni ="12345678B",
            nombre = "Manuela",
            apellidos = "Carmela",
            rol = "PROFESOR",
            password = "passw0rd",
            nfc = null))

    val registros = listOf(
        RegistroAcceso(
            id = "1",
            fechaHora = LocalDateTime.now(),
            accesoPermitido = true,
            mensaje = "Acceso permitido",
            usuario = usuarios[0]
        ),
        RegistroAcceso(
            id = "2",
            fechaHora = LocalDateTime.now(),
            accesoPermitido = false,
            mensaje = "Acceso denegado",
            usuario = usuarios[1]
        )
    )

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { route ->
            when (route) {
                /*
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
                 */

                is HomeKey -> NavEntry(route) {
                    HomeRoute(
                        onSearchClick = {
                            backStack.add(SearchKey)
                        },
                        onSettingsClick = { /*TODO*/ },
                        usuario = usuarios[0],
                        registros = registros
                    )
                }

                is SearchKey -> NavEntry(route) {
                    SearchScreen(
                        lista = usuarios,
                        onResultClick = { /*TODO*/ },
                        onBack = { backStack.removeLastOrNull() },
                    )
                }

                else -> error("Ruta no reconocida: $route")
            }
        }
    )
}