package com.example.mentoria.core.presentation.screens.home

sealed interface HomeEvent {
    object LoggedOut: HomeEvent
    object ActivateNFC: HomeEvent

    object OnSearch: HomeEvent
    object OnSettings: HomeEvent // TODO: esto no
    object OnBack: HomeEvent
}
