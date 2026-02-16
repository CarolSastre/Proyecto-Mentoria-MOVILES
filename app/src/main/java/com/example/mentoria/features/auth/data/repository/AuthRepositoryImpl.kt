package com.example.mentoria.features.auth.data.repository

import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.domain.util.RsaHelper
import com.example.mentoria.features.auth.data.local.AuthLocalDataSource
import com.example.mentoria.core.data.remote.mappers.toDomain
import com.example.mentoria.features.auth.data.remote.AuthRemoteDataSource
import com.example.mentoria.features.auth.data.remote.dto.LoginRequest
import com.example.mentoria.features.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl(
    private val remote: AuthRemoteDataSource,
    private val localDataSource: AuthLocalDataSource
) : AuthRepository {

    override suspend fun login(dni: String, passwordRaw: String): Usuario? {
        return try {
            // 1. Encriptar
            val passwordEncrypted = RsaHelper.encrypt(passwordRaw)
            if (passwordEncrypted.isEmpty()) return null

            // 2. Petición
            val request = LoginRequest(dni = dni, password = passwordEncrypted)

            // 3. Red
            val response = remote.login(request)

            // 4. Guardar Token
            response.token?.let {
                localDataSource.saveToken(it)
            }

            println("Login Response Usuario: ${response.usuario}")

            // 5. Mapear Usuario (Ahora sí encuentra toDomain() gracias al import correcto)
            response.usuario?.toDomain()

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun register(dni: String, password: String): Usuario {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        localDataSource.clearToken() // Ahora funciona porque actualizamos la interfaz en el paso 3
    }

    override suspend fun isUserLoggedIn(): Boolean { // TODO: si devolvemos un flow este no es necesario
        return localDataSource.getToken() != null
    }

    override fun getSessionState(): Flow<Boolean> {
        return localDataSource.getTokenFlow().map { !it.isNullOrBlank() }
    }
}