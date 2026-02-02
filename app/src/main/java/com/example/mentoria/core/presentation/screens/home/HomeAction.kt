package com.example.mentoria.core.presentation.screens.home

sealed interface HomeAction {
    object LoggedOut : HomeAction
    object ActivateNFC : HomeAction

    object OnSearch : HomeAction

    object OnSettings : HomeAction
    object OnBack : HomeAction
}