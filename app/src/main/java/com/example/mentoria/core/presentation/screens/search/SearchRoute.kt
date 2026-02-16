package com.example.mentoria.core.presentation.screens.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mentoria.core.presentation.ObserveAsEvents
import org.koin.compose.koinInject

@Composable
fun SearchRoute(
    viewModel: SeachViewModel = koinInject(),
    onNavigateToUsuario: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.events.ObserveAsEvents { event ->
        when(event) {
            is SearchUiEvent.OnSelectUser -> onNavigateToUsuario(event.userId)
        }
    }

    fun onAction(action: SearchUiAction) {
        when (action) {
            is SearchUiAction.OnUsuarioSelected -> onNavigateToUsuario(action.id)
            else -> viewModel.onAction(action)
        }
    }

    SearchScreen(
        state = uiState,
        onAction = viewModel::onAction,
    )
}