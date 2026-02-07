package com.example.mentoria.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.mentoria.core.domain.model.Departamento
import com.example.mentoria.core.domain.model.RegistroAcceso
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.presentation.screens.SearchScreen
import com.example.mentoria.core.presentation.screens.home.HomeRoute
import com.example.mentoria.features.auth.presentation.login.LoginRoute
import com.example.mentoria.features.auth.presentation.register.RegisterRoute
import java.time.LocalDate
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    startDestination: NavKey = LoginKey,
    //sessionManager: SessionManager = koinInject()
) {
    val backStack = rememberNavBackStack(startDestination)
    // TODO: quitar todo esto de prueba
    val usuarios = listOf(
        Usuario(
            id = "1",
            dni = "12345678A",
            nombre = "Carolina",
            apellidos = "Sastre Garrido",
            rol = Rol.ALUMNO,
            password = "passw0rd",
            nfc = null,
            fechaNacimiento = LocalDate.parse("2001-06-12"),
            gmail = "carolina@gmail.com",
            baja = false,
            curso = "7DMT",
            departamento = null
        ), Usuario(
            id = "2",
            dni = "12345678A",
            nombre = "Profesor",
            apellidos = "Xavier",
            rol = Rol.PROFESOR,
            password = "xavier1",
            nfc = null,
            fechaNacimiento = LocalDate.now(),
            gmail = "xavier@gmail.com",
            baja = false,
            curso = null,
            departamento = Departamento(
                id="1",
                nombre="Ciencias"
            ),
        )
    )

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
    CompositionLocalProvider {
        LocalOnNavigationBack provides { backStack.removeLastOrNull() }
    }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { route ->
            when (route) {
                /*
                is DetallesKey -> NavEntry(route) {
                    DetallesKey(
                        id = route.id,
                    )
                }
                */
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
                    )
                }

                is HomeKey -> NavEntry(route) {
                    HomeRoute(
                        onSearchClick = {
                            backStack.add(SearchKey)
                        },
                        onSettingsClick = { /*TODO*/ },
                        //
                        usuario = usuarios[0],
                        registros = registros
                    )
                }

                is SearchKey -> NavEntry(route) {
                    SearchScreen(
                        onResultClick = { /*TODO*/ },
                        //
                        lista = usuarios,
                    )
                }

                else -> error("Ruta no reconocida: $route")
            }
        }
    )
}