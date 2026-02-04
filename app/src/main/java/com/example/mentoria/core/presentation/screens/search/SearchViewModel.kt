package com.example.mentoria.core.presentation.screens.search

import androidx.lifecycle.ViewModel
import com.example.mentoria.core.domain.repositories.DataStoreRepository
import com.example.mentoria.core.domain.repositories.RegistrosRepository
import com.example.mentoria.core.presentation.screens.StartUiState
import com.example.mentoria.core.presentation.screens.home.HomeEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class SearchViewModel(
    private val dataStoreRepository: DataStoreRepository,
    private val registroRepository: RegistrosRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(StartUiState.Loading)
    val uiState = _uiState.asStateFlow()
    private val _evenChannel = Channel<HomeEvent>()
    val events = _evenChannel.receiveAsFlow()

}