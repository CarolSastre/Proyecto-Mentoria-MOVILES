package com.example.mentoria.core.data.repositories

import com.example.mentoria.core.data.remote.MentoriaApiService
import com.example.mentoria.core.data.remote.mappers.toDomain
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.domain.repositories.UsuariosRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlin.coroutines.EmptyCoroutineContext.get

class UsuariosRepositoryRemoteImpl(
    private val api: MentoriaApiService
) : UsuariosRepository {

    private val _refreshTrigger = MutableSharedFlow<Unit>(
        replay = 1
    )
    val refreshTrigger = _refreshTrigger.asSharedFlow()

    override suspend fun fetchUsuarios(page: Int): List<Usuario> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchProfesores(page: Int): List<Usuario> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchUsuarios(
        query: String,
        page: Int
    ): List<Usuario> {
        TODO("Not yet implemented")
    }
}