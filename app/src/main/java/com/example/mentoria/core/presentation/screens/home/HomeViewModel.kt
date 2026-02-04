package com.example.mentoria.core.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentoria.core.domain.repositories.DataStoreRepository
import com.example.mentoria.core.domain.repositories.RegistrosRepository
import com.example.mentoria.core.domain.repositories.UsuariosRepository
import com.example.mentoria.core.presentation.screens.StartUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val dataStoreRepository: DataStoreRepository,
    private val usuarioRepository: UsuariosRepository,
): ViewModel() {
    private val _uiState = MutableStateFlow(StartUiState.Loading)
    val uiState = _uiState.asStateFlow()
    private val _evenChannel = Channel<HomeEvent>()
    val events = _evenChannel.receiveAsFlow()

fun onAction(action: HomeAction) {
    when (action) {
        HomeAction.ActivateNFC -> onActivateNFC()
        else -> "Acción no reconocida"
    }
}

    fun onActivateNFC() {
        viewModelScope.launch {
            _evenChannel.send(HomeEvent.ActivateNFC)
        }
    }
    fun onSearch() {
        //notifyEvent(HomeEvent.OnSearch)
    }

    fun error(message: String) {
        viewModelScope.launch {
            //_evenChannel.send(HomeEvent.ActivateNFC)
        }
    }
}