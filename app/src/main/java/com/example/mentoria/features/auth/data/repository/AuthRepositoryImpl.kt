package com.example.mentoria.features.auth.data.repository

import com.example.mentoria.core.data.remote.UsuarioApiService
import com.example.mentoria.core.data.remote.dto.UsuarioDto
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.data.remote.mappers.toDomain
import com.example.mentoria.features.auth.data.local.SessionManager
import com.example.mentoria.features.auth.data.remote.AuthRemoteDataSourceImpl
import com.example.mentoria.features.auth.data.remote.dto.LoginRequest
import com.example.mentoria.features.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl(
    private val sessionManager: SessionManager,
    private val remote: AuthRemoteDataSourceImpl,
    //private val localDataSource: AuthLocalDataSource,
    private val usuarioApi: UsuarioApiService,
) : AuthRepository {

    private val _currentUser = MutableStateFlow<Usuario?>(null)
    override val currentUser: StateFlow<Usuario?> = _currentUser.asStateFlow()

    override suspend fun login(dni: String, passwordRaw: String): Usuario? {
        return try {
            // Petición
            val request = LoginRequest(dni = dni, password = passwordRaw)

            val response = remote.login(request)

            //Guardar Token
            response.token?.let { token ->
                val usuarioDto = response.usuario
                //localDataSource.saveToken(it)
                usuarioDto?._id?.let { id ->
                    sessionManager.saveSession(token, id)
                    sessionManager.setCurrentUser(usuarioDto.toDomain())
                }

                if (usuarioDto != null) {
                    _currentUser.value = usuarioDto.toDomain()
                    println("AuthRepository: Usuario actualizado en memoria: ${usuarioDto.nombre}") // Log de control
                } else {
                    println("AuthRepository: ¡ALERTA! El usuario viene nulo del login")
                }
            }

            // Mapear Usuario
            return response.usuario?.toDomain()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun register(
        usuarioDto: UsuarioDto
    ): Unit {
        return try {
            val response = remote.register(usuarioDto)
            println("Respuesta: $response")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun logout() {
        sessionManager.clearSession()
        _currentUser.value = null
    }

    override fun getSessionState(): Flow<Boolean> {
        return sessionManager.getTokenFlow().map { !it.isNullOrBlank() }
    }

    // Esta es la función que llama el MainViewModel al iniciar
    override suspend fun fetchCurrentUser() {
        try {
            // 1. Recuperamos el ID guardado
            val userId = sessionManager.userIdFlow.firstOrNull()

            if (userId != null) {
                // 2. Pedimos los datos frescos a la API
                usuarioApi.getUsuarioById(userId).let {
                    _currentUser.value = it.toDomain()
                }
            } else {
                // No hay ID, no estamos logueados realmente
                logout()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // Si falla la red o el token caducó -> Logout
            logout()
        }
    }
}