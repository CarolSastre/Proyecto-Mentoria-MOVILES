package com.example.mentoria.features.auth.presentation.register

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentoria.features.auth.domain.usecases.RegisterUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
): ViewModel() {
   private val _uiState = MutableStateFlow(RegisterUiState())
   val uiState = _uiState.asStateFlow()

   private val _eventChannel = Channel<RegisterUiEvent>()
   val events = _eventChannel.receiveAsFlow()

    // TODO: seguramente aquí haga falta más info
    @RequiresApi(Build.VERSION_CODES.O)
    fun register(
        dni: String,
        nombre: String,
        apellidos: String,
        password: String,
        fechaNacimiento: String,
        gmail: String
    ){
        viewModelScope.launch {
            _uiState.value = RegisterUiState(isLoading = true)

            runCatching {
                registerUseCase(
                    dni = dni,
                    nombre = nombre,
                    apellidos = apellidos,
                    password = password,
                    fechaNacimiento = fechaNacimiento,
                    gmail = gmail
                )
            }.onSuccess{
                _uiState.value = RegisterUiState()
                _eventChannel.send(RegisterUiEvent.RegisterSuccess)
            }.onFailure {
                _uiState.value = RegisterUiState(
                    error = it.message ?: "Error inesperado"
                )
            }
        }
    }

    fun onError(message: String) {
        viewModelScope.launch {
            _eventChannel.send(RegisterUiEvent.Error)
        }
    }

}