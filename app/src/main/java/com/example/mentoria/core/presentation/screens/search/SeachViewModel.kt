package com.example.mentoria.core.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentoria.core.domain.usecase.GetUsuariosUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SeachViewModel(
    private val getUsuariosUseCase: GetUsuariosUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    private val _eventChannel = Channel<SearchUiEvent>()
    val events = _eventChannel.receiveAsFlow()

    init {
        getUsuariosUseCase()
            .onEach { usuarios ->
                _uiState.value.copy(usuarios = usuarios)
            }
    }

    fun onAction(action: SearchUiAction) {
        when (action) {
            is SearchUiAction.OnExpandedChange -> expandedChange(action.expanded)
            is SearchUiAction.OnQueryChange -> queryChange(action.query)
            is SearchUiAction.OnUsuarioSelected -> usuarioSelected(action.id)
        }
    }

    private fun usuarioSelected(id: String) {
        viewModelScope.launch {
            _eventChannel.send(SearchUiEvent.OnSelectUser(id))
        }
    }

    private fun expandedChange(expanded: Boolean) {
        viewModelScope.launch {
            _uiState.value.copy(expanded = expanded)
        }
    }

    private fun queryChange(query: String) {
        viewModelScope.launch {
            _uiState.value.copy(query = query)
        }
    }
}