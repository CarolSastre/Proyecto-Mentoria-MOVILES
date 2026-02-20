package com.example.mentoria.core.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.mentoria.core.presentation.components.UsuarioDetailsCard
import java.time.LocalDate
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeUiState,
    snackBar: SnackbarHostState = remember { SnackbarHostState() },
    onQueryChange: (String) -> Unit,
    onAction: (HomeUiAction) -> Unit = {},
) {
    val searchResults = remember { state.usuarios.toMutableList() }
    var toggleSearchBar by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(state.query) {
        searchResults.clear()
        if (state.query.isNotEmpty()) {
            searchResults.addAll(
                state.usuarios.filter {
                    it.nombre.startsWith(
                        prefix = state.query,
                        ignoreCase = true,
                    ) ||
                            it.apellidos.startsWith(
                                prefix = state.query,
                                ignoreCase = true,
                            )
                },
            )
        }
    }

    MainScaffold(
        modifier = modifier,
        usuario = state.usuario,
        title = "Inicio",
        snackBar = snackBar,
        onAction = onAction,
        onSearchClick = { toggleSearchBar = !toggleSearchBar },
        toggle = toggleSearchBar
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (toggleSearchBar) {
                SearchBar(
                    modifier = Modifier.fillMaxWidth(),
                    query = state.query,
                    onQueryChange = onQueryChange,
                    onSearch = { toggleSearchBar = !toggleSearchBar },
                    active = toggleSearchBar,
                    onActiveChange = { toggleSearchBar = it },
                    placeholder = { Text("Buscar usuario") }
                ) {
                    val searchResults = state.usuarios.filter {
                        it.nombre.startsWith(prefix = state.query, ignoreCase = true) ||
                                it.apellidos.startsWith(prefix = state.query, ignoreCase = true)
                    }
                    LazyColumn {
                        if (state.usuarios.isNotEmpty()) items(
                            items = searchResults,
                            key = { it.id }
                        ) { usuarioLista ->
                            UsuarioDetailsCard(
                                usuario = usuarioLista,
                                navigateToDetail = {
                                    onAction(HomeUiAction.OnUsuarioSelected(usuarioLista.id))
                                },
                                toggleSelection = {},
                            )
                        } else item {
                            Text(
                                text = "No hay usuarios",
                                modifier = Modifier.padding(16.dp),
                            )
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Card(
                        modifier = Modifier
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
                            val nombre = state.usuario?.nombre ?: "Invitado"
                            Text(
                                text = "Hola, ${nombre}!",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text("Bienvenido a la pantalla principal")
                        }
                    }
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        if (state.registros.isNotEmpty()) items(
                            items = state.registros,
                            key = { it.id }
                        ) { registro ->
                            if (state.usuario?.id == registro.usuario.id)
                                RegistroDetailsCard(
                                    registro = registro,
                                    navigateToDetail = {},
                                    toggleSelection = {},
                                    onDeleteRegistro = {},
                                    modificable = state.usuario.rol != Rol.ALUMNO
                                )
                        } else item {
                            Text(
                                text = "No hay registros de este usuario",
                                modifier = Modifier.padding(16.dp),
                            )
                        }
                    }
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
            departamento = null,
            fotoPerfilUrl = null,
            password = ""
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
                id = "1",
                nombre = "Ciencias"
            ),
            fotoPerfilUrl = null,
            password = ""
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
        onQueryChange = {},
        onAction = {}
    )
}