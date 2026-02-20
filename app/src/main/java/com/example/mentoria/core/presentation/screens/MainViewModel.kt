package com.example.mentoria.core.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.features.auth.data.local.SessionManager
import com.example.mentoria.features.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: AuthRepository,
) : ViewModel() {
    // 1. Convertimos el flujo del Usuario (del Repo) en un Estado de UI
    val uiState: StateFlow<StartUiState> = repository.currentUser // 👈 Observamos al USUARIO
        .map { usuario ->
            if (usuario != null) StartUiState.Logged else StartUiState.NotLogged
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = StartUiState.Loading // Empieza cargando
        )

    init {
        viewModelScope.launch {
            repository.fetchCurrentUser() // Intenta recuperar los datos
        }
    }
}