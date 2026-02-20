package com.example.mentoria.core.presentation.screens.home

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.mentoria.core.presentation.ObserveAsEvents
import com.example.mentoria.features.auth.domain.repository.AuthRepository
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.KoinApplication.Companion.init

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = koinViewModel(),
    onLoggedOut: () -> Unit,
    onCalendarioClick: () -> Unit,
    onHorarioClick: () -> Unit,
    onNavigateToUsuario: (String) -> Unit,
){

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    viewModel.events.ObserveAsEvents { event ->
        when(event) {
            is HomeUiEvent.OnSelectUser -> onNavigateToUsuario(event.userId)
            HomeUiEvent.LoggedOut -> onLoggedOut()
            HomeUiEvent.OnCalendario -> onCalendarioClick()
            HomeUiEvent.OnHorario -> onHorarioClick()
            HomeUiEvent.ActivateNFC -> snackbarHostState.showSnackbar("Función aún no implementada")
        }
    }

    fun onAction(action: HomeUiAction) {
        when(action) {
            is HomeUiAction.OnUsuarioSelected -> onNavigateToUsuario(action.id)
            else -> viewModel.onAction(action)
        }
    }

    HomeScreen (
        state = uiState.value,
        snackBar = snackbarHostState,
        onQueryChange = { newQuery ->
            onAction(HomeUiAction.OnQueryChange(newQuery))
        },
        onAction = {
            action -> onAction(action)
        }
    )
}