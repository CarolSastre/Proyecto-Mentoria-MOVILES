package com.example.mentoria.core.presentation.screens

sealed interface StartUiState {
    data object Loading : StartUiState
    data object Logged : StartUiState
    data object NotLogged : StartUiState
}