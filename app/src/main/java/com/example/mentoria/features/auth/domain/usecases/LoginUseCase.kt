package com.example.mentoria.features.auth.domain.usecases

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.features.auth.domain.repository.AuthRepository
import java.time.LocalDate

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(dni: String, password: String): Usuario? {
        // Validacion
        if (dni.isBlank() || password.isBlank()) {
            throw IllegalArgumentException("Dni o contraseña en blanco")
        }
        if (dni != "test" || password != "1234")
            throw IllegalArgumentException("Dni o contraseña incorrectos")
        else return Usuario(
            id = "0",
            dni = "12345678A",
            nombre = "alumno",
            apellidos = "prueba",
            rol = Rol.ALUMNO,
            nfc = null,
            fechaNacimiento = LocalDate.now(),
            gmail = "test@gmail.com",
            baja = false,
            curso = "7DMT",
            departamento = null
        )
        //else return authRepository.login(dni, password)
    }
}
