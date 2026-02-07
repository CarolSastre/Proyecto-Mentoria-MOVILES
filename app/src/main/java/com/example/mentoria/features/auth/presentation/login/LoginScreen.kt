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
    loadingProgressBar: Boolean = false,
    imageError: Boolean = false,
    onLoginClick: (String, String) -> Unit,
    onCreateAccountClick: () -> Unit
) {
    var email by rememberSaveable { mutableStateOf(value = "test") }
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

            TextOutOfTextField (
                text = email,
                title = "DNI",
                placeholder = "Ej: 000000000A",
                onValueChange = { email = it },
                onClearButton = { email = "" }
            )

            PasswordOutTextField(
                textValue = password,
                onValueChange = { password = it },
                onDone = {
                    focusManager.clearFocus()
                }
            )

            Button(
                onClick = {
                    onLoginClick(email, password)
                    // TODO: pasar los datos de autentificación
                },
                modifier = Modifier.width(200.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 5.dp),
                enabled = !state.isLoading,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "Iniciar sesión",
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 25.sp,
                )
            }

            state.error?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Button(
                onClick = {
                    onCreateAccountClick()
                },
                modifier = Modifier.width(200.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 5.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "Registrarse",
                    fontSize = 25.sp,
                    //color = Color.White
                )
            }
        }

        if (imageError) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                /*
            Image(
                painter = painterResource(
                    id = // TODO R.drawable.ic_error_imagen
                        ),
                contentDescription = "Image Error",
                modifier = modifier.size(250.dp)

            )
            */
            }
        }

        if (loadingProgressBar) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.85f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = Color.Blue,
                    strokeWidth = 5.dp,
                    modifier = modifier.size(60.dp)
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
        onLoginClick = { _, _ -> },
        onCreateAccountClick = {}
    )
}