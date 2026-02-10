package com.example.mentoria.features.auth.domain.usecases

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.features.auth.domain.repository.AuthRepository
import java.time.LocalDate

class RegisterUseCase(
    private val repository: AuthRepository
) { // TODO: confirmar tda la info a padir
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(dni: String, password: String): Usuario {
        return Usuario(
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
        //return repository.register(dni, password)
    }
}
