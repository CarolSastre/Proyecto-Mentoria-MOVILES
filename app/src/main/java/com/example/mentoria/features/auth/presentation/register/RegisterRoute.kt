package com.example.mentoria.features.auth.presentation.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mentoria.core.presentation.ObserveAsEvents
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterRoute(
    onRegisterSuccess: () -> Unit,
    onBack: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.events.ObserveAsEvents { event ->
        when(event) {
            RegisterUiEvent.RegisterSuccess -> onRegisterSuccess()
        }
    }

    RegisterScreen(
        state = state,
        onRegisterClick = viewModel::register,
        onBack = onBack
    )
}