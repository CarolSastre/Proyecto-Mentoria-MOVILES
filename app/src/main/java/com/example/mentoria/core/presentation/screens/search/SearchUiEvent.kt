package com.example.mentoria.core.presentation.screens.search

sealed interface SearchUiEvent {
    data class OnSelectUser(val userId: String): SearchUiEvent
}