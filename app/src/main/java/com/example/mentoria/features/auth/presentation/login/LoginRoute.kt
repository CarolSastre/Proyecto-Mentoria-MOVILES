package com.example.mentoria.features.auth.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mentoria.core.presentation.ObserveAsEvents
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginRoute(
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.events.ObserveAsEvents {event ->
        when(event) {
            LoginUiEvent.LoginSuccess -> onLoginSuccess()
        }
    }

    LoginScreen(
        state = state,
        onLoginClick = viewModel::login,
        onCreateAccountClick = onNavigateToRegister
    )
}