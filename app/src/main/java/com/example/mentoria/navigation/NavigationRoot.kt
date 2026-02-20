package com.example.mentoria.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.mentoria.core.data.MockDataProvider
import com.example.mentoria.core.domain.model.Departamento
import com.example.mentoria.core.domain.model.Horario
import com.example.mentoria.core.domain.model.RegistroAcceso
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.presentation.ObserveAsEvents
import com.example.mentoria.core.presentation.screens.calendario.CalendarioRoute
import com.example.mentoria.core.presentation.screens.crearUsuario.CrearUsuarioRoute
import com.example.mentoria.core.presentation.screens.home.HomeRoute
import com.example.mentoria.core.presentation.screens.horario.HorarioRoute
import com.example.mentoria.core.presentation.screens.usuariodetails.UsuarioDetailsRoute
import com.example.mentoria.features.auth.data.local.SessionEvent
import com.example.mentoria.features.auth.data.local.SessionManager
import com.example.mentoria.features.auth.presentation.login.LoginRoute
import com.example.mentoria.features.auth.presentation.register.RegisterRoute
import org.koin.compose.koinInject
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

val LocalOnNavigationBack = compositionLocalOf<() -> Unit> { { } }

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    startDestination: NavKey = LoginKey,
    sessionManager: SessionManager = koinInject()
) {
    val backStack = rememberNavBackStack(startDestination)
    val tokenState = sessionManager.getTokenFlow().collectAsStateWithLifecycle(initialValue = null)
    val usuario = sessionManager.userFlow.collectAsStateWithLifecycle(initialValue = null)

    // Efecto: Si el token desaparece (null), volvemos al Login
    LaunchedEffect(tokenState) {
        if (tokenState == null) {
            backStack.clear()
            backStack.add(LoginKey)
        }
    }

    sessionManager.events.ObserveAsEvents { event ->
        when (event) {
            SessionEvent.LoggedOut -> {
                backStack.clear()
                backStack.add(LoginKey)
            }
        }
    }

    CompositionLocalProvider(LocalOnNavigationBack provides { backStack.removeLastOrNull() }) {
        NavDisplay(
            backStack = backStack,
            onBack = { if (backStack.size > 1) backStack.removeLastOrNull() },
            entryProvider = { key ->
                NavEntry(key) {
                    when (key) {
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
                                    backStack.removeLastOrNull() // vuelve a la pantalla de Login
                                },
                            )

                        is HomeKey ->
                            HomeRoute(
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
                                onNavigateToUsuario = { usuarioId ->
                                    backStack.add(UsuarioDetailsKey(usuarioId))
                                },
                                onCrearProfesor = { tipo ->
                                    backStack.add(CrearUsuarioKey(tipo))
                                },
                                onCrearAlumno = { tipo ->
                                    backStack.add(CrearUsuarioKey(tipo))
                                }
                            )

                        is UsuarioDetailsKey ->
                            UsuarioDetailsRoute(
                                usuarioId = key.id,
                            )

                        is CrearUsuarioKey ->
                            CrearUsuarioRoute(
                                tipo = key.tipo,
                                onRegisterSuccess = {
                                    backStack.removeLastOrNull()
                                }
                            )

                        is CalendarKey ->
                            CalendarioRoute()

                        is HorarioKey ->
                            HorarioRoute(
                                usuario = MockDataProvider.usuarios[0],
                                horarios = MockDataProvider.horarios
                            )

                        else -> error("Ruta no reconocida: $key")
                    }
                }
            }
        )
    }
}