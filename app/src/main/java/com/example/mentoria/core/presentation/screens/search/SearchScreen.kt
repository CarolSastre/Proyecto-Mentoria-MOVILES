package com.example.mentoria.core.presentation.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mentoria.R
import com.example.mentoria.core.domain.model.Departamento
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.presentation.components.ProfileImage
import com.example.mentoria.navigation.LocalOnNavigationBack
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    state: SearchUiState,
    onAction: (SearchUiAction) -> Unit = {},
    onBack: () -> Unit = LocalOnNavigationBack.current,
) {
    val searchResults = remember { state.usuarios.toMutableList() }
    val onExpandedChange: (Boolean) -> Unit = {
        onAction(SearchUiAction.OnExpandedChange(it))
    }
    val onQueryChange: (String) -> Unit = {
        onAction(SearchUiAction.OnQueryChange(it))
    }

    // para el route de detalles
    /**
     id: String
     viewModel: SearchViewModel = koinViewModel{
        parametersOf(
            id= id
        )
    }
     */

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
                    /*|| // TODO: se puede hacer que busque por nombre o por departamento
                            it.departamento.nombre.startsWith(
                                prefix =
                                    query,
                                ignoreCase = true,
                            )
                            */
                },
            )
        }
    }

    Box(
        modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }
            .background(MaterialTheme.colorScheme.surface)
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = 0f },
            inputField = {
                SearchBarDefaults.InputField(
                    query = state.query,
                    onQueryChange = {
                        onQueryChange(it)
                    },
                    onSearch = {
                        onExpandedChange(false)
                    },
                    expanded = state.expanded,
                    onExpandedChange = onExpandedChange,
                    placeholder = { Text(text = "Buscar nombre o apellido...") },
                    leadingIcon = {
                        if (state.expanded) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBackIosNew,
                                contentDescription = "Back button",
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .clickable {
                                        onExpandedChange(false)
                                        onQueryChange("")
                                        onBack()
                                    },
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = "Search",
                                modifier = Modifier.padding(start = 16.dp),
                            )
                        }
                    },
                )
            },
            expanded = state.expanded,
            onExpandedChange = onExpandedChange,
        ) {
            if (searchResults.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    items(
                        items = searchResults,
                        key = { it.id }
                    ) { usuario ->
                        if (usuario.rol.toString() != "ADMIN") {
                            ListItem(
                                headlineContent = { Text("${usuario.nombre} ${usuario.apellidos}") },
                                supportingContent = { Text(usuario.rol.toString()) },
                                leadingContent = {
                                    ProfileImage(
                                        drawableResource = R.drawable.prueba_background,
                                        description = "Profile Photo",
                                        modifier = Modifier
                                            .size(32.dp),
                                    )
                                },
                                modifier = Modifier.clickable {
                                    onAction(SearchUiAction.OnUsuarioSelected(usuario.id))
                                    onQueryChange("")
                                    onExpandedChange(false)
                                },
                            )
                        }
                    }
                }
            } else if (state.query.isNotEmpty()) {
                Text(
                    text = "No se ha encontrado ningún resultado",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.inverseSurface,
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun SearchScreenPreview() {
    val lista = listOf(
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
            dni = "12345678A",
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
    SearchScreen(
        state = SearchUiState(
            query = "",
            usuarios = lista,
            isLoading = false,
        ),
        onAction = {},
    )
}