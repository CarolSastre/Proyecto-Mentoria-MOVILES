package com.example.mentoria.core.presentation.screens.calendario

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.mentoria.core.data.MockDataProvider
import com.example.mentoria.core.presentation.ObserveAsEvents
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CalendarioRoute(
    viewModel: CalendarioViewModel = koinViewModel(),
){
    val snackbarHostState = remember { SnackbarHostState() }

    viewModel.events.ObserveAsEvents { event ->
        when(event) {
            else -> snackbarHostState.showSnackbar("Error desconocido")
        }
    }

    CalendarioScreen(
        registros = MockDataProvider.registros, // viewModel.registros.value,
        onDateSelected = {},
    )
}