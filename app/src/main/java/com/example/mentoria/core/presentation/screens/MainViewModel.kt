package com.example.mentoria.core.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentoria.features.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    // Convertimos el Flow<Boolean> del repo en un UiState
    val uiState: StateFlow<StartUiState> = repository.getSessionState()
        .map { isLoggedIn ->
            if (isLoggedIn) StartUiState.Logged else StartUiState.NotLogged
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = StartUiState.Loading // ⚠️ IMPORTANTE: Empieza cargando
        )
}