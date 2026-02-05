package com.example.mentoria.core.presentation.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.mentoria.core.domain.model.RegistroAcceso
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.presentation.ObserveAsEvents
import org.koin.compose.viewmodel.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeRoute(
    //onLoggedOut: () -> Unit,
    onSearchClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onBack: () -> Unit = {},
    usuario: Usuario,
    registros: List<RegistroAcceso>,
    viewModel: HomeViewModel = koinViewModel()
){

    val snackbarHostState = remember { SnackbarHostState() }

    viewModel.events.ObserveAsEvents { event ->
        when(event) {
        /*
            HomeUiEvent.LoggedOut -> onLoggedOut()
         */
            HomeUiEvent.OnBack -> onBack()
            HomeUiEvent.OnSearch -> onSearchClick()
            HomeUiEvent.ActivateNFC -> snackbarHostState.showSnackbar("Función aún no implementada")
            else -> snackbarHostState.showSnackbar("Error desconocido")
        }
    }

    HomeScreen ( // TODO: poner mejor los métodos que se van a usar
        //onLogOut = viewModel::onLogOut,
        onSearchClick = viewModel::onSearch,
        snackBar = snackbarHostState,
        onSettingsClick = onSettingsClick,
        onNFCClick = viewModel::onActivateNFC,
        onBack = viewModel::onBack,
        registros = registros,
        usuario = usuario
    )
}