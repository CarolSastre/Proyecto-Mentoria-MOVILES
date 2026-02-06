package com.example.mentoria.features.auth.presentation.login

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mentoria.core.presentation.ObserveAsEvents
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginRoute(
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    viewModel: LoginViewModel = koinInject()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBar = remember { SnackbarHostState() }

    viewModel.events.ObserveAsEvents {event ->
        when(event) {
            LoginUiEvent.LoginSuccess -> onLoginSuccess()
            LoginUiEvent.OnRegister -> onNavigateToRegister()
        }
    }

    LoginScreen(
        state = state,
        snackBar = snackBar,
        onLoginClick = viewModel::login,
        onCreateAccountClick = viewModel::register
    )
}