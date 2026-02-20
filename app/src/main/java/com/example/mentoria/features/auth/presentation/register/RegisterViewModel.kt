package com.example.mentoria.features.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentoria.core.data.remote.dto.UsuarioDto
import com.example.mentoria.features.auth.domain.usecases.RegisterUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    private val _events = Channel<RegisterUiEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: RegisterUiAction) {
        when (action) {
            is RegisterUiAction.OnRegisterClick -> register(
                action.usuarioDto,
                action.passwordConfirmation
            )
        }
    }

    private fun register(usuario: UsuarioDto, passwordConfirmation: String) {
        if (usuario.password != passwordConfirmation) {
            _uiState.update { it.copy(error = "Las contraseñas no coinciden") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val result = runCatching {
                registerUseCase(usuario)
            }

            result.onSuccess {
                _uiState.update { it.copy(isLoading = false, error = "Usuario Registrado") }
                _events.send(RegisterUiEvent.RegisterSuccess)
            }.onFailure { exception ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = exception.message ?: "Error desconocido durante el registro"
                    )
                }
            }
        }
    }
}