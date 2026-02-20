package com.example.mentoria.core.presentation.screens.crearUsuario

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mentoria.core.presentation.ObserveAsEvents
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CrearUsuarioRoute(
    onRegisterSuccess: () -> Unit,
    tipo: String,
    viewModel: CrearUsuarioViewModel  = koinViewModel(key = tipo) { parametersOf(tipo) },
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBar = remember { SnackbarHostState() }

    viewModel.events.ObserveAsEvents { event ->
        when (event) {
            CrearUsuarioUiEvent.RegisterSuccess -> onRegisterSuccess()
            else -> snackBar.showSnackbar("Error desconocido")
        }
    }

    // Mostrar snackbar si hay error
    LaunchedEffect(state.error) {
        state.error?.let {
            snackBar.showSnackbar(it)
        }
    }

    RegisterScreen(
        state = state,
        snackBar = snackBar,
        onRegisterClick = { action ->
            viewModel.onAction(action)
        },
    )
}