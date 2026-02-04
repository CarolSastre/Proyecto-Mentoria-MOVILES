package com.example.mentoria.core.presentation.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Nfc
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mentoria.R
import com.example.mentoria.core.domain.model.RegistroAcceso
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.presentation.components.MainTopAppBar
import com.example.mentoria.core.presentation.components.ProfileImage
import com.example.mentoria.core.presentation.components.RegistroDetailsCard
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    snackBar: SnackbarHostState = remember { SnackbarHostState() },
    usuario: Usuario,
    registros: List<RegistroAcceso>,
    onSearchClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onBack: () -> Unit = {},
    onNFCClick: () -> Unit = {},
    //onLogOut: () -> Unit,
    //
) {
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBar,
            )
        },
        topBar = {
            MainTopAppBar(
                title = "",
                usuario = usuario,
                onSearchClick = onSearchClick,
                onSettingsClick = onSettingsClick,
                onBackClick = onBack,
                //onLogOut = onLogOut
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(
                    top = 5.dp
                )
                .padding(paddingValues = innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .clip(CardDefaults.shape),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = "Hola, ${usuario.nombre}!",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text("Bienvenido a la pantalla principal")
                }
            }

            FloatingActionButton(
                onClick = { onNFCClick() },
                modifier = Modifier.padding(top = 8.dp, bottom = 32.dp),
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            ) {
                Icon(
                    imageVector = Icons.Filled.Nfc,
                    contentDescription = "NFC",
                    modifier = Modifier.size(18.dp),
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(
                    items = registros,
                    key = { it.id }
                ) { registro ->
                    RegistroDetailsCard(
                        registro = registro,
                        navigateToDetail = {},
                        toggleSelection = {},
                        onDeleteRegistro = {}
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    val alumnas = listOf(
        Usuario(
            dni = "12345678A",
            nombre = "Carolina",
            apellidos = "Sastre Garrido",
            rol = Rol.ADMIN,
            password = "passw0rd",
            nfc = null
        ), Usuario(
            dni = "12345678B",
            nombre = "Manuela",
            apellidos = "Carmela",
            rol = Rol.PROFESOR,
            password = "passw0rd",
            nfc = null
        )
    )

    val lista = listOf(
        RegistroAcceso(
            id = "1",
            fechaHora = LocalDateTime.now(),
            accesoPermitido = true,
            mensaje = "Acceso permitido",
            usuario = alumnas[0]
        ),
        RegistroAcceso(
            id = "2",
            fechaHora = LocalDateTime.now(),
            accesoPermitido = false,
            mensaje = "Acceso denegado",
            usuario = alumnas[1]
        )
    )

    HomeScreen(
        snackBar = SnackbarHostState(),
        onSearchClick = {},
        onSettingsClick = {},
        onBack = {},
        registros = lista,
        usuario = alumnas[0]
        //onLogOut = {}
    )
}