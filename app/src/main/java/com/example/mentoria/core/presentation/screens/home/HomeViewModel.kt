package com.example.mentoria.core.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.domain.usecase.GetAllUsuariosUseCase
import com.example.mentoria.features.auth.domain.repository.AuthRepository
import com.example.mentoria.features.auth.domain.usecases.LogoutUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class HomeViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val authRepository: AuthRepository,
    private val getAllUsuariosUseCase: GetAllUsuariosUseCase,
    //private val getRegistrosFromUsuarioUseCase: GetRegistrosFromUsuarioUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val _eventChannel = Channel<HomeUiEvent>()
    val events = _eventChannel.receiveAsFlow()

    private val _usuarios = MutableStateFlow<List<Usuario>>(emptyList<Usuario>())
    val usuarios = _usuarios.asStateFlow()

    init {
        observeCurrentUser()
        val result = getAllUsuariosUseCase()

        viewModelScope.launch {
            result.collect {
                _usuarios.value = it
            }
        }
    }

    fun onAction(action: HomeUiAction) {
        when(action) {
            is HomeUiAction.OnUsuarioSelected -> usuarioSelected(action.id)
            is HomeUiAction.OnQueryChange -> queryChange(action.query)
            is HomeUiAction.OnSearchClick -> searchClick(action.expanded)
            HomeUiAction.OnLogOutClick -> onLogOut()
            HomeUiAction.OnCalendarioClick -> onCalendario()
            HomeUiAction.OnHorarioClick -> onHorario()
            HomeUiAction.ActivateNFC -> onActivateNFC()
        }
    }

    private fun observeCurrentUser() {
        viewModelScope.launch {
            authRepository.currentUser.collect { usuario ->
                _uiState.update { currentState ->
                    currentState.copy(usuario = usuario)
                }
            }
        }
    }

    private fun notifyEvent(event: HomeUiEvent) {
        viewModelScope.launch {
            _eventChannel.send(event)
        }
    }

    fun onLogOut() {
        viewModelScope.launch {
            logoutUseCase()
        }
    }

    fun onActivateNFC() {
        notifyEvent(event = HomeUiEvent.ActivateNFC)
    }

    fun onCalendario() {
        notifyEvent(HomeUiEvent.OnCalendario)
    }

    fun onHorario() {
        notifyEvent(HomeUiEvent.OnHorario)
    }

    private fun usuarioSelected(id: String) {
        notifyEvent(HomeUiEvent.OnSelectUser(id))
    }

    private fun searchClick(expanded: Boolean) {
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