package com.example.mentoria.core.presentation.screens.usuariodetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.domain.usecase.GetUsuarioUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn

class UsuarioDetailsViewModel(
    usuarioId: String,
    private val getUsuarioUseCase: GetUsuarioUseCase,
): ViewModel() {
    val state: StateFlow<Usuario?> = getUsuarioUseCase(usuarioId)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )

    private val _eventChannel = Channel<UsuarioDetailsEvent>()
    val events = _eventChannel.receiveAsFlow()

    fun onAction(action: UsuarioDetailsAction) {
        when(action) {
            // TODO: borrar usuario si se es profesor o admin ?
            else -> {}
        }
    }


}