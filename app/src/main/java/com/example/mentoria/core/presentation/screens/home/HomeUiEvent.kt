package com.example.mentoria.core.presentation.screens.home

sealed interface HomeUiEvent {
    object LoggedOut: HomeUiEvent
    object OnSearch: HomeUiEvent
    object ActivateNFC: HomeUiEvent
}