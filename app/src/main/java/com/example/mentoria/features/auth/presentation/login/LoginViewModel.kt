package com.example.mentoria.features.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentoria.features.auth.domain.usecases.LoginUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _eventChannel = Channel<LoginUiEvent>()
    val events = _eventChannel.receiveAsFlow()

    fun onAction(action: LoginUiAction) {
        when (action) {
            is LoginUiAction.OnLoginClick -> login(action.dni, action.password)
            LoginUiAction.OnRegisterClick -> register()
        }
    }

    private fun register() {
        viewModelScope.launch {
            _eventChannel.send(LoginUiEvent.OnRegister)
        }
    }

    private fun login(dni: String, password: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true, error = null)
            }

            val result = runCatching {
                loginUseCase(dni, password)
            }

            result.onSuccess { usuario ->
                _uiState.update { it.copy(isLoading = false) }
                if (usuario != null) {
                    _eventChannel.send(LoginUiEvent.LoginSuccess)
                    // TODO: guardar el token?
                } else {
                    _uiState.update { it.copy(error = "Dni o contraseña incorrecta") }
                }
            }.onFailure { exception ->
                _uiState.update {
                    it.copy(isLoading = false, error = exception.message ?: "Error desconocido")
                }
            }
        }
    }
}
