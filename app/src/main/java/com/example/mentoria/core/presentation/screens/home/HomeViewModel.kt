package com.example.mentoria.core.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentoria.main.presentation.StartUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    //private val logoutUseCase: LogoutUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(StartUiState.Loading)
    val uiState = _uiState.asStateFlow()
    private val _evenChannel = Channel<HomeUiEvent>()
    val events = _evenChannel.receiveAsFlow()

    private fun notifyEvent(event: HomeUiEvent) {
        viewModelScope.launch {
            _evenChannel.send(event)
        }
    }

    fun onLogOut(){
        viewModelScope.launch {
            //logoutUseCase()
        }
    }

    fun onActivateNFC() {
        notifyEvent(event = HomeUiEvent.ActivateNFC)
    }

    fun onBack(){
        //notifyEvent(event = HomeUiEvent.OnBack)
    }

    fun onSearch() {
        notifyEvent(HomeUiEvent.OnSearch)
    }
}