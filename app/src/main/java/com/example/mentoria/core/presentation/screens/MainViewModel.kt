package com.example.mentoria.core.presentation.screens

import androidx.lifecycle.ViewModel
import com.example.mentoria.core.presentation.screens.StartUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(
    //private val isUserLoggedIn: IsUserLoggedInUseCase
): ViewModel() {
    private val _uiState =
        MutableStateFlow<StartUiState>(StartUiState.Loading)

    val uiState = _uiState.asStateFlow()

    init {
        /*
        viewModelScope.launch {
            val logged = isUserLoggedIn()
            _uiState.value = if (logged) {
                StartUiState.Logged
            } else {
                StartUiState.NotLogged
            }
        }
         */
    }
}