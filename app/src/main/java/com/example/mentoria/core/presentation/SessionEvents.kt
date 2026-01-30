package com.example.mentoria.core.presentation

sealed interface SessionEvent {
    data object LoggedOut : SessionEvent
}