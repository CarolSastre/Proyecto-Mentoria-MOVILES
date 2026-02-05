package com.example.mentoria.core.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mentoria.R
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.presentation.components.ProfileImage
import java.time.LocalDate
import kotlin.toString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    lista: List<Usuario>,
    onResultClick: (Usuario) -> Unit,
    onBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val searchResults = remember { mutableStateListOf<Usuario>() }
    val onExpandedChange: (Boolean) -> Unit = {
        expanded = it
    }

    LaunchedEffect(query) {
        searchResults.clear()
        if (query.isNotEmpty()) {
            searchResults.addAll(
                lista.filter {
                    it.nombre.startsWith(
                        prefix = query,
                        ignoreCase = true,
                    ) ||
                            it.apellidos.startsWith(
                                prefix = query,
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
                    query = query,
                    onQueryChange = {
                        query = it
                    },
                    onSearch = {
                        expanded = false
                    },
                    expanded = expanded,
                    onExpandedChange = onExpandedChange,
                    placeholder = { Text(text = "Buscar...") },
                    leadingIcon = {
                        if (expanded) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBackIosNew,
                                contentDescription = "Back button",
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .clickable {
                                        expanded = false
                                        query = ""
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
                    //trailingIcon = trailingIcon
                )
            },
            expanded = expanded,
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
                                    onResultClick.invoke(usuario)
                                    query = ""
                                    expanded = false
                                },
                            )
                        }
                    }
                }
            } else if (query.isNotEmpty()) {
                Text(
                    text = "No se ha encontrado ningún resultado",
                    modifier = Modifier.padding(16.dp),
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun SearchScreenPreview() {
    val lista = listOf(
        Usuario(
            id = "1",
            dni = "12345678A",
            nombre = "Carolina",
            apellidos = "Sastre Garrido",
            rol = Rol.ADMIN,
            password = "passw0rd",
            nfc = null,
            gmail = "carolsaga02@gmail.com",
            fechaNacimiento = LocalDate.now(),
            departamento = null,
            curso = null,
            baja = false,
        ), Usuario(
            dni = "12345678B",
            nombre = "Manuela",
            apellidos = "Carmela",
            rol = Rol.PROFESOR,
            password = "passw0rd",
            nfc = null,
            id = "2",
            gmail = "carolsaga02@gmail.com",
            fechaNacimiento = LocalDate.now(),
            departamento = null,
            curso = null,
            baja = false,
        )
    )
    SearchScreen(
        lista = lista,
        onResultClick = {}
    )
}