package com.example.mentoria.features.auth.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.features.auth.data.remote.AuthRemoteDataSource
import com.example.mentoria.features.auth.domain.repository.AuthRepository
import java.time.LocalDate

/**
 * Implementación del repositorio de autenticación.
 *
 * Esta clase es el único punto de verdad (Single Source of Truth) para los datos de autenticación.
 * Se comunica con las fuentes de datos (en este caso, solo la remota) para obtener y enviar información.
 *
 * @param remote El data source que se encarga de las llamadas a la API de autenticación.
 */
class AuthRepositoryImpl(
    private val remote: AuthRemoteDataSource
) : AuthRepository {

    /**
     * Realiza el proceso de login.
     * 1. Llama al data source remoto con las credenciales.
     * 2. El data source devuelve un `UsuarioDto` (modelo de datos de la API).
     * 3. Este método se encarga de mapear (convertir) el `UsuarioDto` a un `Usuario` (modelo de dominio).
     * 4. Devuelve el modelo de dominio `Usuario`, que es el que la app usa internamente.
     *
     * @param email El email del usuario.
     * @param password La contraseña del usuario.
     * @return El objeto `Usuario` si el login es exitoso, o null si falla.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun login(email: String, password: String): Usuario? {
        /* TODO: descomentar cuando esté la api
        // Envolvemos la llamada en un bloque try-catch para manejar errores de red o del servidor.
        return try {
            // Creamos el objeto de la petición que espera la API.
            val request = LoginRequest(email = email, password = password)

            // Llamamos a la función del data source.
            val usuarioDto = remote.login(request)

            // Mapeamos el DTO al modelo de dominio.
            // Aquí es donde ocurre la transformación de la capa de datos a la de dominio.
            usuarioDto?.toDomain()
        } catch (e: Exception) {
            // Si ocurre cualquier excepción (ej: sin conexión, error 404, 500),
            // registramos el error y devolvemos null para indicar que el login falló.
            // En una app real, aquí podrías usar una clase sellada para devolver errores más específicos.
            e.printStackTrace() // Imprime el error en Logcat para depuración.
            null
        }
         */

        // TODO: borrar cuando esté la api
        if (email != "Profesor" || password != "profesor") {
            return null
        } else {
            return Usuario(
                id = "0",
                dni = "12345678A",
                nombre = "alumno",
                apellidos = "prueba",
                rol = Rol.ALUMNO,
                password = "1234",
                nfc = null,
                fechaNacimiento = LocalDate.now(),
                gmail = "test@gmail.com",
                baja = false,
                curso = "7DMT",
                departamento = null
            )
        }
    }

    override suspend fun register(
        email: String,
        password: String
    ): Usuario {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override suspend fun isUserLoggedIn(): Boolean {
        TODO("Not yet implemented")
    }

    // Aquí irían otras implementaciones de la interfaz AuthRepository, como register(), logout(), etc.
}

