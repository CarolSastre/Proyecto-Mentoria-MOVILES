package com.example.mentoria.core.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.example.mentoria.features.auth.domain.usecases.LogoutUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    //private val logoutUseCase: LogoutUseCase
): ViewModel() {
    private val _evenChannel = Channel<HomeUiEvent>()
    val events = _evenChannel.receiveAsFlow()
/*
    fun onLogOut(){
        viewModelScope.launch {
            logoutUseCase()
        }
    }

 */

    fun onNFC() {
        viewModelScope.launch {
            _evenChannel.send(HomeUiEvent.NFC)
        }
    }
}

sealed interface HomeEvent {
    //data object LoggedOut: HomeEvent
}