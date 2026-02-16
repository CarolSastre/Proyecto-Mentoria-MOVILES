package com.example.mentoria.core.presentation.screens.search

sealed interface SearchUiAction {
    data class OnUsuarioSelected(val id: String): SearchUiAction
    data class OnQueryChange(val query: String) : SearchUiAction
    data class OnExpandedChange(val expanded: Boolean) : SearchUiAction
    //object OnBackClick : SearchUiAction
}