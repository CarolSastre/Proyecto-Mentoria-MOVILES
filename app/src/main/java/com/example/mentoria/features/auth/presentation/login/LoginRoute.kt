package com.example.mentoria.features.auth.presentation.login

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mentoria.core.presentation.ObserveAsEvents
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginRoute(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBar = remember { SnackbarHostState() }

    viewModel.events.ObserveAsEvents { event ->
        when (event) {
            LoginUiEvent.LoginSuccess -> onLoginSuccess()
            LoginUiEvent.OnRegister -> onNavigateToRegister()
        }
    }

    fun onAction(action: LoginUiAction) {
        when (action) {
            is LoginUiAction.OnLoginClick -> viewModel.onAction(action)
            LoginUiAction.OnRegisterClick -> viewModel.onAction(action)
        }
    }

    // Mostrar snackbar si hay error
    LaunchedEffect(state.error) {
        state.error?.let {
            snackBar.showSnackbar(it)
        }
    }

    LoginScreen(
        state = state,
        snackBar = snackBar,
        onAction = { action ->
            onAction(action)
        },
        /*
        onLoginClick = {
            dni, password -> onAction(LoginUiAction.OnLoginClick(dni, password))
        },
        onCreateAccountClick = {
            onAction(LoginUiAction.OnRegisterClick)
        }*/
    )
}