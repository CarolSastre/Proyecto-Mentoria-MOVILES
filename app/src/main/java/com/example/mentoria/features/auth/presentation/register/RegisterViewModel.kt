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
        val password = usuario.password

        // 1. Validar que los campos obligatorios no estén vacíos
        if (usuario.dni.isNullOrBlank() || usuario.nombre.isNullOrBlank() || usuario.apellidos.isNullOrBlank() || password.isNullOrBlank()) {
            _uiState.update { it.copy(error = "DNI, nombre, apellidos y contraseña son obligatorios") }
            return
        }

        // 2. Validar que las contraseñas coinciden
        if (password != passwordConfirmation) {
            _uiState.update { it.copy(error = "Las contraseñas no coinciden") }
            return
        }

        // 3. Si la validación es correcta, iniciar el proceso de registro
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val result = runCatching {
                registerUseCase.invoke(usuario)
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