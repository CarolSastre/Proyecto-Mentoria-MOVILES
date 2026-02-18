package com.example.mentoria.core.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.domain.usecase.GetAllUsuariosUseCase
import com.example.mentoria.features.auth.data.local.SessionManager
import com.example.mentoria.features.auth.domain.repository.AuthRepository
import com.example.mentoria.features.auth.domain.usecases.LogoutUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class HomeViewModel(
    private val session: SessionManager,
    private val logoutUseCase: LogoutUseCase,
    private val authRepository: AuthRepository,
    private val getAllUsuariosUseCase: GetAllUsuariosUseCase,
    //private val getRegistrosFromUsuarioUseCase: GetRegistrosFromUsuarioUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())

    /* SI VUELVE A FALLAR EL USUARIO EN HOME ES POR ESTO
     */
    val uiState: StateFlow<HomeUiState> = authRepository.currentUser
        .map { usuario ->
            HomeUiState(usuario = usuario)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState()
        )

    private val _evenChannel = Channel<HomeUiEvent>()
    val events = _evenChannel.receiveAsFlow()

    private val _usuarios = MutableStateFlow<List<Usuario>>(emptyList<Usuario>())
    val usuarios = _usuarios.asStateFlow()

    init {
        observeCurrentUser()
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
            _evenChannel.send(event)
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

    fun onSearch() {
        notifyEvent(HomeUiEvent.OnSearch)
    }

    fun onCalendario() {
        notifyEvent(HomeUiEvent.OnCalendario)
    }

    fun onHorario() {
        notifyEvent(HomeUiEvent.OnHorario)
    }
}