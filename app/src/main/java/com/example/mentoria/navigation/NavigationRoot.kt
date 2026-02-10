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
import com.example.mentoria.core.domain.model.Horario
import com.example.mentoria.core.domain.model.RegistroAcceso
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.presentation.screens.SearchScreen
import com.example.mentoria.core.presentation.screens.calendario.CalendarioRoute
import com.example.mentoria.core.presentation.screens.home.HomeRoute
import com.example.mentoria.core.presentation.screens.horario.HorarioRoute
import com.example.mentoria.features.auth.presentation.login.LoginRoute
import com.example.mentoria.features.auth.presentation.register.RegisterRoute
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    startDestination: NavKey = LoginKey,
    //sessionManager: SessionManager = koinInject()
) {
    val backStack = rememberNavBackStack(startDestination)
    // TODO: quitar todo esto ///////////////
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
                id = "1",
                nombre = "Ciencias"
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

    val horarios = listOf(
        Horario("1", "Mates", "Lunes", LocalTime.of(8, 0), LocalTime.of(9, 30)),
        Horario("2", "Física", "Martes", LocalTime.of(9, 0), LocalTime.of(11, 0)),
        Horario("3", "Historia", "Miércoles", LocalTime.of(11, 0), LocalTime.of(12, 0)),
        Horario("4", "Kotlin", "Jueves", LocalTime.of(15, 0), LocalTime.of(17, 0)),
        Horario("5", "Inglés", "Viernes", LocalTime.of(8, 0), LocalTime.of(10, 0)),
        Horario("6", "Deporte", "Viernes", LocalTime.of(10, 0), LocalTime.of(11, 30))
    )

    ////////////////////////////////////////

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

    NavDisplay(
        backStack = backStack,
        entryProvider = { route ->
            NavEntry(route) {
                CompositionLocalProvider(LocalOnNavigationBack provides { backStack.removeLastOrNull() }) {
                    when (route) {
                        /*
                    is DetallesKey -> NavEntry(route) {
                        DetallesKey(
                            id = route.id,
                        )
                    }
                    */

                        is LoginKey ->
                            LoginRoute(
                                onLoginSuccess = {
                                    backStack.add(HomeKey)
                                },
                                onNavigateToRegister = {
                                    backStack.add(RegisterKey)
                                }
                            )

                        is RegisterKey ->
                            RegisterRoute(
                                onRegisterSuccess = {
                                    backStack.add(HomeKey)
                                },
                            )

                        is HomeKey ->
                            HomeRoute(
                                onSearchClick = {
                                    backStack.add(SearchKey)
                                },
                                onLoggedOut = {
                                    backStack.clear()
                                    backStack.add(LoginKey)
                                },
                                onCalendarioClick = {
                                    backStack.add(CalendarKey)
                                },
                                onHorarioClick = {
                                    backStack.add(HorarioKey)
                                },
                                //
                                usuario = usuarios[0],
                                registros = registros
                            )

                        is SearchKey ->
                            SearchScreen(
                                onResultClick = { /*TODO*/ },
                                //
                                lista = usuarios,
                            )

                        is CalendarKey ->
                            CalendarioRoute()

                        is HorarioKey ->
                            HorarioRoute(
                                usuario = usuarios[0],
                                horarios = horarios
                            )

                        else -> error("Ruta no reconocida: $route")
                    }
                }
            }
        }
    )
}