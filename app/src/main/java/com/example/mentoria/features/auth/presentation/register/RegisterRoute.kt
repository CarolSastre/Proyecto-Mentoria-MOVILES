package com.example.mentoria.features.auth.presentation.register

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mentoria.core.presentation.ObserveAsEvents
import com.example.mentoria.navigation.LocalOnNavigationBack
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterRoute(
    onRegisterSuccess: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBar = remember { SnackbarHostState() }

    viewModel.events.ObserveAsEvents { event ->
        when(event) {
            RegisterUiEvent.RegisterSuccess -> onRegisterSuccess()
            else -> snackBar.showSnackbar("Error desconocido")
        }
    }

    RegisterScreen(
        state = state,
        snackBar = snackBar,
        onRegisterClick = viewModel::register,
    )
}