package com.example.mentoria.features.auth.domain.usecases

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.features.auth.domain.repository.AuthRepository
import java.time.LocalDate

class RegisterUseCase(
    private val repository: AuthRepository
) { // TODO: confirmar tda la info a pedir
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(
        dni: String,
        nombre: String,
        apellidos: String,
        password: String,
        fechaNacimiento: String,
        gmail: String
        ): Usuario {
        return Usuario(
            id = "2",
            dni = dni,
            nombre = nombre,
            apellidos = apellidos,
            password = password,
            fechaNacimiento = LocalDate.parse(fechaNacimiento),
            gmail = gmail,
            rol = Rol.ALUMNO,
            baja = false,
            curso = null,
            departamento = null,
            nfc = null
        )
        //return repository.register(dni, password)
    }
}
