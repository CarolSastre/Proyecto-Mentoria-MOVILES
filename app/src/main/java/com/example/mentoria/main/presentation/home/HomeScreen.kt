package com.example.mentoria.main.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Nfc
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.presentation.components.MainTopAppBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onBack: () -> Unit = {},
    onLogOut: () -> Unit,
    //
) {
    Scaffold(
        topBar = {
            MainTopAppBar (
                title = "Usuario",
                onSearchClick = onSearchClick,
                onSettingsClick = onSettingsClick,
                onBackClick = onBack,
                usuario = Usuario(
                    dni = "12345678A",
                    nombre = "Carolina",
                    apellidos = "Sastre Garrido",
                    rol = "ADMIN",
                    password = "passw0rd",
                    nfc = null
                )
                //onLogOut = onLogOut
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 24.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            Text("Hola!")
            Text("Bienvenido a la pantalla principal")
            Icon(
                imageVector = Icons.Default.Nfc,
                contentDescription = "NFC"
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onSearchClick = {},
        onSettingsClick = {},
        onBack = {},
        onLogOut = {}
    )
}