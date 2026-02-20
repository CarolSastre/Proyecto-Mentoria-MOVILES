package com.example.mentoria.core.presentation.screens.crearUsuario

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mentoria.core.data.remote.dto.DepartamentoDto
import com.example.mentoria.core.data.remote.dto.UsuarioDto
import com.example.mentoria.core.presentation.components.MainTopAppBar
import com.example.mentoria.features.auth.presentation.components.PasswordOutTextField
import com.example.mentoria.features.auth.presentation.components.TextOutOfTextField
import com.example.mentoria.navigation.LocalOnNavigationBack
import java.util.Locale
import java.util.Locale.getDefault

@Composable
fun RegisterScreen(
    state: CrearUsuarioUiState,
    snackBar: SnackbarHostState = remember { SnackbarHostState() },
    onRegisterClick: (CrearUsuarioUiAction) -> Unit,
    onBack: () -> Unit = LocalOnNavigationBack.current,
) {
    var dni by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var gmail by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }
    var curso by remember { mutableStateOf("") }
    var departamento by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBar,
            )
        },
        topBar = {
            MainTopAppBar(
                title = "Crear nuevo ${state.tipo}",
                onBack = onBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 32.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextOutOfTextField( // dni
                text = dni,
                title = "DNI",
                placeholder = "Ej: 000000000A",
                onValueChange = { dni = it },
                onClearButton = { dni = "" }
            )

            TextOutOfTextField( // nombre
                text = nombre,
                title = "Nombre",
                placeholder = "Ej: Rafa",
                onValueChange = { nombre = it },
                onClearButton = { nombre = "" }
            )

            TextOutOfTextField( // apellidos
                text = apellidos,
                title = "Apellidos",
                placeholder = "Ej: Puig",
                onValueChange = { apellidos = it },
                onClearButton = { apellidos = "" }
            )
            if (state.tipo == "Alumno") {
                TextOutOfTextField( // curso
                    text = curso,
                    title = "Curso",
                    placeholder = "7DMT",
                    onValueChange = { curso = it },
                    onClearButton = { curso = "" }
                )
            } else {
                TextOutOfTextField( // departamento
                    text = departamento,
                    title = "Departamento",
                    placeholder = "Informática",
                    onValueChange = { departamento = it },
                    onClearButton = { departamento = "" }
                )
            }

            TextOutOfTextField( // gmail
                text = gmail,
                title = "Correo electrónico",
                placeholder = "Ej: rafa@ejemplo.com.",
                onValueChange = { gmail = it },
                onClearButton = { gmail = "" }
            )

            PasswordOutTextField(
                textValue = password,
                onValueChange = { password = it },
                onDone = {
                    focusManager.clearFocus()
                }
            )

            PasswordOutTextField(
                textValue = password2,
                onValueChange = { password2 = it },
                onDone = {
                    focusManager.clearFocus()
                }
            )

            TextOutOfTextField( // fechaNacimiento
                text = fechaNacimiento,
                title = "Fecha de nacimiento",
                placeholder = "dd/MM/yyyy",
                onValueChange = { fechaNacimiento = it },
                onClearButton = { fechaNacimiento = "" }
            )

            Button(
                onClick = {
                    val usuarioDto = UsuarioDto(
                        dni = dni,
                        nombre = nombre,
                        apellidos = apellidos,
                        password = password,
                        fechaNacimiento = fechaNacimiento.ifBlank { null },
                        gmail = gmail,
                        rol = state.tipo.uppercase(getDefault()),
                        baja = false,
                        curso = curso.ifBlank { null },
                        departamento = departamento.ifBlank { null } as DepartamentoDto?,
                        fotoPerfil = null
                    )
                    onRegisterClick(
                        CrearUsuarioUiAction.OnRegisterClick(
                            usuarioDto = usuarioDto,
                            passwordConfirmation = password2
                        )
                    )
                },
                enabled = !state.isLoading,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Crear usuario")
            }

            state.error?.let {
                Spacer(Modifier.height(8.dp))
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        state = CrearUsuarioUiState(),
        onRegisterClick = { _ -> },
        onBack = {}
    )
}
