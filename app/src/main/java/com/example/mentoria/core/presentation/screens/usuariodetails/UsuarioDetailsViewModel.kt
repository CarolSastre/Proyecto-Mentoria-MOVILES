package com.example.mentoria.core.presentation.screens.usuariodetails

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.domain.usecase.GetUsuarioUseCase
import com.example.mentoria.features.auth.data.local.SessionManager
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsuarioDetailsViewModel(
    usuarioId: String,
    private val getUsuarioUseCase: GetUsuarioUseCase,
    private val session: SessionManager,
): ViewModel() {
    private val _uiState = MutableStateFlow<Usuario?>(null)
    val state= _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val token = session.getToken()
            getUsuarioUseCase(usuarioId).collect { usuario ->
                _uiState.value = usuario
            }
        }
    }
    private val _eventChannel = Channel<UsuarioDetailsEvent>()
    val events = _eventChannel.receiveAsFlow()

    fun onAction(action: UsuarioDetailsAction) {
        when(action) {
            else -> {}
        }
    }


}