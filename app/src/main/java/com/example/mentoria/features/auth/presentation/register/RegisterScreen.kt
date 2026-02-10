package com.example.mentoria.features.auth.presentation.register

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mentoria.features.auth.presentation.components.PasswordOutTextField
import com.example.mentoria.features.auth.presentation.components.TextOutOfTextField
import com.example.mentoria.navigation.LocalOnNavigationBack

@Composable
fun RegisterScreen(
    state: RegisterUiState,
    snackBar: SnackbarHostState = remember { SnackbarHostState() },
    onRegisterClick: (String, String, String, String, String, String) -> Unit,
    onBack: () -> Unit = LocalOnNavigationBack.current,
) {
    var dni by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var gmail by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBar,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Crear cuenta de alumno",
                style = MaterialTheme.typography.headlineMedium
            )

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

            Button( // TODO: mandar más info
                onClick = {
                    // TODO: esto debería verificarse en el usecase, el error saldrá al gracias a state.error de después
                    if (password != password2) state.copy(error = "Las contraseñas no coinciden")
                    else onRegisterClick(dni, password, nombre, apellidos, fechaNacimiento, gmail)
                },
                enabled = !state.isLoading,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Registrarse")
            }

            state.error?.let {
                Spacer(Modifier.height(8.dp))
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }

            TextButton(onClick = onBack) {
                Text("Volver")
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
        state = RegisterUiState(),
        onRegisterClick = { _, _, _, _, _, _ -> },
        onBack = {}
    )
}
