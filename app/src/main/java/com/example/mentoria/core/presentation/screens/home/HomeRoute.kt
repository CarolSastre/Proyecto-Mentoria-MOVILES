package com.example.mentoria.core.presentation.screens.home

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mentoria.core.presentation.ObserveAsEvents
import org.koin.compose.koinInject

@Composable
fun HomeRoute(
    onLoggedOut: () -> Unit,
    onSearchClick: () -> Unit,
    onCalendarioClick: () -> Unit,
    onHorarioClick: () -> Unit,
    viewModel: HomeViewModel = koinInject(),
){

    // val visible by viewModel.uiState.collectAsStateWithLifecycle()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    viewModel.events.ObserveAsEvents { event ->
        when(event) {
            HomeUiEvent.LoggedOut -> onLoggedOut()
            HomeUiEvent.OnSearch -> onSearchClick()
            HomeUiEvent.OnCalendario -> onCalendarioClick()
            HomeUiEvent.OnHorario -> onHorarioClick()
            HomeUiEvent.ActivateNFC -> snackbarHostState.showSnackbar("Función aún no implementada")
        }
    }

    HomeScreen ( // TODO: poner mejor los métodos en un action
        state = uiState.value,
        snackBar = snackbarHostState,
        onLogOut = viewModel::onLogOut,
        onNFCClick = viewModel::onActivateNFC,
        onSearchClick = viewModel::onSearch,
        onCalendarioClick = viewModel::onCalendario,
        onHorarioClick = viewModel::onHorario,
    )
}