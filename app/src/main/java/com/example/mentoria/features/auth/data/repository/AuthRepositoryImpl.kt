package com.example.mentoria.features.auth.data.repository

import com.example.mentoria.core.data.remote.UsuarioApiService
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.domain.util.RsaHelper
import com.example.mentoria.core.data.remote.mappers.toDomain
import com.example.mentoria.features.auth.data.local.SessionManager
import com.example.mentoria.features.auth.data.remote.AuthRemoteDataSource
import com.example.mentoria.features.auth.data.remote.AuthRemoteDataSourceImpl
import com.example.mentoria.features.auth.data.remote.dto.LoginRequest
import com.example.mentoria.features.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class AuthRepositoryImpl(
    private val sessionManager: SessionManager,
    private val remote: AuthRemoteDataSourceImpl,
    //private val localDataSource: AuthLocalDataSource,
    private val usuarioApi: UsuarioApiService,
) : AuthRepository {
    init {
        println("Repo HASH: ${this.hashCode()}")
    }
    // Variable privada mutable
    private val _currentUser = MutableStateFlow<Usuario?>(
        null
        /*
        Usuario(
            id = "69935128cd34aa5b7685a3f4",
            dni = "10000000P",
            nombre="Profesor1",
            apellidos="Docente1",
            nfc = "NFC_PROFE_1",
            rol = Rol.PROFESOR,
            fechaNacimiento = LocalDate.parse("1979-12-31"),
            gmail = "profe1@instituto.com",
            departamento = Departamento(
                id = "69935128cd34aa5b7685a3f0",
                nombre = "Informática"
            ),
            baja = false,
            curso = null,
        )
         */
    )

    // Variable pública inmutable (la que ven los ViewModels)
    override val currentUser: StateFlow<Usuario?> = _currentUser.asStateFlow()

    override suspend fun login(dni: String, passwordRaw: String): Usuario? {
        return try {
            // 1. Encriptar
            //val passwordEncrypted = RsaHelper.encrypt(passwordRaw)
            //if (passwordEncrypted.isEmpty()) return null

            // 2. Petición
            val request = LoginRequest(dni = dni, password = passwordRaw)

            // 3. Red
            val response = remote.login(request)

            // 4. Guardar Token
            response.token?.let { token ->
                val usuarioDto = response.usuario
                //localDataSource.saveToken(it)
                usuarioDto?.id?.let { id ->
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

            // 5. Mapear Usuario
            return response.usuario?.toDomain()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun register(dni: String, password: String): Usuario {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        sessionManager.clearSession()
        _currentUser.value = null
    }

    override suspend fun isUserLoggedIn(): Boolean { // TODO: si devolvemos un flow este no es necesario
        return sessionManager.getToken() != null
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
                usuarioApi.getUsuarioById(userId).collect { usuario ->
                    // 3. ¡Bingo! Tenemos usuario, actualizamos el estado
                    _currentUser.update { usuario.toDomain() }
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