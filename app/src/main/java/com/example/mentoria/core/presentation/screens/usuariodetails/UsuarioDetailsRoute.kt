package com.example.mentoria.core.presentation.screens.usuariodetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.mentoria.core.presentation.ObserveAsEvents
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun UsuarioDetailsRoute (
    usuarioId: String,
    viewModel: UsuarioDetailsViewModel = koinViewModel(key = usuarioId) { parametersOf(usuarioId) },
) {
    val state by viewModel.state.collectAsState()

    viewModel.events.ObserveAsEvents { event ->
        when(event) {
            else -> {}
        }
    }

    UsuarioDetailsScreen(
        usuario = state,
        onAction = viewModel::onAction
    )
}