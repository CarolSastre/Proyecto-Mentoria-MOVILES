package com.example.mentoria.core.presentation.screens.home

import com.example.mentoria.core.presentation.screens.search.SearchUiAction

sealed interface HomeUiAction {
    object OnCalendarioClick : HomeUiAction
    object OnHorarioClick : HomeUiAction
    object OnLogOutClick : HomeUiAction
    object ActivateNFC : HomeUiAction
    data class OnUsuarioSelected(val id: String): HomeUiAction
    data class OnQueryChange(val query: String) : HomeUiAction
    data class OnSearchClick(val expanded: Boolean) : HomeUiAction // OnExpandedClick
}