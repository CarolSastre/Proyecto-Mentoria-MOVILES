package com.example.mentoria.core.presentation.screens.horario

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.example.mentoria.core.domain.model.Horario
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.presentation.ObserveAsEvents
import com.example.mentoria.core.presentation.screens.home.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HorarioRoute (
    viewModel: HorarioViewModel = koinViewModel(),
    usuario: Usuario,
    horarios: List<Horario>
) {
    val uiState = MutableStateFlow(HomeUiState().usuario).collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    viewModel.events.ObserveAsEvents { event ->
        when (event) {
            else -> snackbarHostState.showSnackbar("Error desconocido")
        }
    }

    HorarioScreen(
        usuario = uiState.value,
        horarios = horarios
    )
}