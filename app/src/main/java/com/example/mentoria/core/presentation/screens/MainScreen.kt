package com.example.mentoria.core.presentation.screens

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
import com.example.mentoria.core.presentation.components.MainTopAppBar

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onAboutClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onBack: () -> Unit = {}
    //
    //
) {
    Scaffold(
        topBar = {
            MainTopAppBar (
                title = "Usuario",
                onAboutClick = onAboutClick,
                onSearchClick = onSearchClick,
                onSettingsClick = onSettingsClick,
                onBackClick = onBack
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
fun MainScreenPreview() {
    MainScreen()
}