package com.example.mentoria.features.auth.data.remote

import com.example.mentoria.core.data.remote.dto.UsuarioDto
import com.example.mentoria.features.auth.data.remote.dto.LoginRequest

/**
 * Data source para las operaciones de autenticación remotas.
 *
 * Esta clase actúa como un intermediario directo con la API de Retrofit (`AuthApi`).
 * Su única responsabilidad es ejecutar las llamadas de red y devolver los DTOs (Data Transfer Objects)
 * tal como los recibe del servidor. No contiene lógica de negocio ni de mapeo a modelos de dominio.
 *
 * @param authApi Una instancia de la interfaz de Retrofit que define los endpoints de autenticación.
 */
class AuthRemoteDataSource(
    private val authApi: AuthApi
) {

    /**
     * Llama al endpoint de login de la API.
     *
     * @param request El objeto con las credenciales del usuario (email y password).
     * @return Un `UsuarioDto` si la llamada es exitosa, o lanza una excepción si hay un error.
     */
    suspend fun login(request: LoginRequest): UsuarioDto {
        // Simplemente delega la llamada a la interfaz de Retrofit.
        // Retrofit y OkHttp se encargarán de ejecutar la petición de red.
        // Si la respuesta del servidor no es exitosa (ej: 401, 404, 500), Retrofit lanzará una excepción
        // que será capturada por el AuthRepositoryImpl.
        return authApi.login(request)
    }

    // Aquí podrías añadir otras funciones para interactuar con la API, como:
    // suspend fun register(request: RegisterRequest): RegisterResponse {
    //     return authApi.register(request)
    // }
    //
    // suspend fun logout() {
    //     // Podría llamar a un endpoint para invalidar el token en el servidor.
    // }
}
