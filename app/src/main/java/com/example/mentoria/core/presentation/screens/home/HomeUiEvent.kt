package com.example.mentoria.core.presentation.screens.home

sealed interface HomeUiEvent {
    object LoggedOut: HomeUiEvent
    object OnSearch: HomeUiEvent
    object OnCalendario: HomeUiEvent
    object OnHorario: HomeUiEvent
    object ActivateNFC: HomeUiEvent
}