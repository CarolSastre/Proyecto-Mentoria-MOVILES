package com.example.mentoria.core.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.domain.usecase.GetRegistrosFromUsuarioUseCase
import com.example.mentoria.features.auth.domain.usecases.LogoutUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class HomeViewModel(
    private val logoutUseCase: LogoutUseCase,
    //TODO: crear el caso de uso correspondiente
    //private val getRegistrosFromUsuarioUseCase: GetRegistrosFromUsuarioUseCase,
): ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()
    private val _evenChannel = Channel<HomeUiEvent>()
    val events = _evenChannel.receiveAsFlow()

    // TODO: revisar DI>koin>list_navigation_viewmodel (no es para los stateFlow, sino para para las pantallas de detalle)
    private val _usuarios = MutableStateFlow<List<Usuario>>(emptyList<Usuario>())
    val usuarios = _usuarios.asStateFlow()

    /*
    fun observeQuery() { // persistencia>películas
        uiState.map{ it.query }
            .debounce(500.milliseconds)
            .map{ query -> query.trim() }
            .distinctUntilChanged()
            .flatMapLatest { query -> //  (si recoge un flow)
                getFilteredQuotesUseCase(query)
            }
            .onEach{
                quotes ->
                _uiState.update {
                    it.copy(quotes = quotes)
                }
            }
            .launchIn(viewModelScope)
    }
     */

    /*
    init{
        uiState
            .map { it.usuario }
            .distinctUntilChanged()
            .debounce(300L)
            .flatMapLatest { usuario ->
                getRegistrosFromUsuarioUseCase(usuario?.id ?: "")
            }
            .onEach { registros ->
                _uiState.update { it.copy(registros = registros) }
            }
            .launchIn(viewModelScope)
    }*/

    private fun notifyEvent(event: HomeUiEvent) {
        viewModelScope.launch {
            _evenChannel.send(event)
        }
    }

    fun onLogOut(){
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