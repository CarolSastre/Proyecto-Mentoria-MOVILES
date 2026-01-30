package com.example.mentoria.main.presentation.home

import androidx.compose.runtime.Composable
import com.example.mentoria.core.presentation.ObserveAsEvents
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeRoute(
    onLoggedOut: () -> Unit,
    onSearchClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onBack: () -> Unit = {},
    viewModel: HomeViewModel = koinViewModel()
){
    viewModel.events.ObserveAsEvents { event ->
        when(event) {
            HomeUiEvent.LoggedOut -> onLoggedOut()
        }
    }

    HomeScreen ( // TODO: poner mejor los métodos que se van a usar
        onLogOut = viewModel::onLogOut,
        onSearchClick = onSearchClick,
        onSettingsClick = onSettingsClick,
        onBack = onBack
    )
}