package com.example.mentoria.main.presentation.home

sealed interface HomeUiEvent {
    object LoggedOut: HomeUiEvent
}