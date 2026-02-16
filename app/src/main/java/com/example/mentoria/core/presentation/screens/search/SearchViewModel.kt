package com.example.mentoria.core.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentoria.core.domain.usecase.GetAllUsuariosUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getAllUsuariosUseCase: GetAllUsuariosUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    private val _eventChannel = Channel<SearchUiEvent>()
    val events = _eventChannel.receiveAsFlow()

    init {
        getAllUsuariosUseCase()
            .onEach { usuarios ->
                _uiState.value.copy(usuarios = usuarios)
            }
    }

    fun onAction(action: SearchUiAction) {
        when (action) {
            is SearchUiAction.OnExpandedChange -> expandedChange(action.expanded)
            is SearchUiAction.OnQueryChange -> queryChange(action.query)
            is SearchUiAction.OnUsuarioSelected -> usuarioSelected(action.id)
            //SearchUiAction.OnBackClick -> {} // TODO: ?
        }
    }

    private fun usuarioSelected(id: String) {
        viewModelScope.launch {
            _eventChannel.send(SearchUiEvent.OnSelectUser(id))
        }
    }

    private fun expandedChange(expanded: Boolean) {
        viewModelScope.launch {
            _uiState.update { it.copy(expanded = expanded) }
        }
    }

    private fun queryChange(query: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(query = query) }
        }
    }
}