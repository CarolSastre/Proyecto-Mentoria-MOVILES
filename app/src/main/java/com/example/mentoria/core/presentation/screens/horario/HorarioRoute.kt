package com.example.mentoria.core.presentation.screens.horario

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.mentoria.core.domain.model.Horario
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.presentation.ObserveAsEvents
import org.koin.compose.koinInject

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HorarioRoute (
    viewModel: HorarioViewModel = koinInject(),
    //
    usuario: Usuario,
    horarios: List<Horario>
) {
    val snackbarHostState = remember { SnackbarHostState() }

    viewModel.events.ObserveAsEvents { event ->
        when (event) {
            else -> snackbarHostState.showSnackbar("Error desconocido")
        }
    }

    HorarioScreen(
        // TODO: quitar esto
        usuario = usuario,
        horarios = horarios
    )
}