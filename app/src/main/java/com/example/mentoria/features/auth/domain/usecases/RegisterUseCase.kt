package com.example.mentoria.features.auth.domain.usecases

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mentoria.core.data.remote.dto.UsuarioDto
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.features.auth.domain.repository.AuthRepository
import java.time.LocalDate

class RegisterUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        usuarioDto: UsuarioDto
    ): Unit {
        val result = repository.register(usuarioDto)
        return result
    }
}
