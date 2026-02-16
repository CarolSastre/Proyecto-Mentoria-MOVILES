package com.example.mentoria.features.auth.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mentoria.features.auth.presentation.components.PasswordOutTextField
import com.example.mentoria.features.auth.presentation.components.TextOutOfTextField

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    snackBar: SnackbarHostState = remember { SnackbarHostState() },
    state: LoginUiState,
    onAction: (LoginUiAction) -> Unit,
) {
    var dni by rememberSaveable { mutableStateOf(value = "10000000P") }
    var password by rememberSaveable { mutableStateOf(value = "1234") }

    val focusManager = LocalFocusManager.current

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBar,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            /*
        Image(
            painter = painterResource(
                id = // TODO: R.drawable.ic_login_image, añadir logo
                ),
            contentDescription = "Image Login",
            modifier = modifier
                .fillMaxWidth()
                .size(300.dp),
            alignment = Alignment.Center
        )
         */
            Text(
                text = "Login",
                fontSize = 30.sp
            )

            TextOutOfTextField(
                text = dni,
                title = "DNI",
                placeholder = "Ej: 000000000A",
                onValueChange = { dni = it },
                onClearButton = { dni = "" }
            )

            PasswordOutTextField(
                textValue = password,
                onValueChange = { password = it },
                onDone = { focusManager.clearFocus() }
            )

            Button(
                onClick = {
                    focusManager.clearFocus()
                    onAction(LoginUiAction.OnLoginClick(dni, password))
                },
                modifier = Modifier.width(200.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 5.dp),
                enabled = !state.isLoading,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.primary
                )
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(text = "Iniciar Sesión", fontSize = 20.sp)
                }
            }

            Button(
                onClick = {
                    onAction(LoginUiAction.OnRegisterClick)
                },
                modifier = Modifier.width(200.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 5.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "Crear cuenta",
                    fontSize = 20.sp,
                    //color = Color.White
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        state = LoginUiState(),
        onAction = {}
    )
}