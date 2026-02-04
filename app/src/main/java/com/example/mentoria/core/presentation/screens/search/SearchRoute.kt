package com.example.mentoria.core.presentation.screens.search

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.presentation.ObserveAsEvents
import com.example.mentoria.core.presentation.screens.home.HomeEvent
import com.example.mentoria.core.presentation.screens.home.HomeScreen

@Composable
fun ScreenRoute(
    onResultClick: (Usuario) -> Unit,
    onBack: () -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = koinViewModel()
){
    val snackbarHostState = remember { SnackbarHostState() }

    viewModel.events.ObserveAsEvents { event ->
        when(event) {
            /*
                HomeEvent.LoggedOut -> onLoggedOut()
             */
            HomeEvent.OnBack -> onBack()
            HomeEvent.OnSearch -> onSearchClick()
            HomeEvent.ActivateNFC -> snackbarHostState.showSnackbar("Función aún no implementada")
            else -> snackbarHostState.showSnackbar("Error desconocido")
        }
    }

    HomeScreen( // TODO: poner mejor los métodos que se van a usar
        //onLogOut = viewModel::onLogOut,
        onSearchClick = viewModel::onSearch,
        snackBar = snackbarHostState,
        onSettingsClick = onSettingsClick,
        onNFCClick = viewModel::onActivateNFC,
        onBack = onBack,
        registros = registros,
        usuario = usuario
    )
}