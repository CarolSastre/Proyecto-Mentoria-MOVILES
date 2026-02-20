package com.example.mentoria.features.auth.data.local

sealed interface SessionEvent {
    data object LoggedOut : SessionEvent
}