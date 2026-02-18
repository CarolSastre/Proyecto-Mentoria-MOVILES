package com.example.mentoria.core.presentation.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mentoria.core.domain.model.Departamento
import com.example.mentoria.core.domain.model.RegistroAcceso
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.presentation.components.MainScaffold
import com.example.mentoria.core.presentation.components.RegistroDetailsCard
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun HomeScreen(
    state: HomeUiState,
    modifier: Modifier = Modifier,
    snackBar: SnackbarHostState = remember { SnackbarHostState() },
    onNFCClick: () -> Unit = {},
    onSearchClick: () -> Unit,
    onCalendarioClick: () -> Unit,
    onHorarioClick: () -> Unit,
    onLogOut: () -> Unit,
) {
    MainScaffold(
        modifier = Modifier,
        title = "Inicio",
        snackBar = snackBar,
        isNFCButton = true,
        onNFCClick = onNFCClick,
        onSearchClick = onSearchClick,
        onCalendarioClick = onCalendarioClick,
        onHorarioClick = onHorarioClick,
        onLogOut = onLogOut,
        //
        usuario = state.usuario,
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
                    val nombre = state.usuario?.nombre
                    Text(text = "Hola, ${nombre}!",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text("Bienvenido a la pantalla principal")
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(
                    items = state.registros,
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

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    val alumnas = listOf(
        Usuario(
            id = "1",
            dni = "12345678A",
            nombre = "Carolina",
            apellidos = "Sastre Garrido",
            rol = Rol.ALUMNO,
            nfc = null,
            fechaNacimiento = LocalDate.now(),
            gmail = "carolina@gmail.com",
            baja = false,
            curso = "7DMT",
            departamento = null
        ), Usuario(
            id = "2",
            dni = "12345678B",
            nombre = "Profesor",
            apellidos = "Xavier",
            rol = Rol.PROFESOR,
            nfc = null,
            fechaNacimiento = LocalDate.now(),
            gmail = "xavier@gmail.com",
            baja = false,
            curso = null,
            departamento = Departamento(
                id="1",
                nombre="Ciencias"
            ),
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
        state = HomeUiState(
            usuario = alumnas[0],
            registros = lista
        ),
        onSearchClick = {},
        onCalendarioClick = {},
        onHorarioClick = {},
        onLogOut = {}
    )
}