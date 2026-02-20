package com.example.mentoria.core.presentation.screens.home

sealed interface HomeUiEvent {
    data class OnSelectUser(val userId: String): HomeUiEvent
    data class OnCrearProfesor(val tipo: String): HomeUiEvent
    data class OnCrearAlumno(val tipo: String): HomeUiEvent
    object LoggedOut: HomeUiEvent
    //object OnSearch: HomeUiEvent //
    object OnCalendario: HomeUiEvent
    object OnHorario: HomeUiEvent
    object ActivateNFC: HomeUiEvent
}