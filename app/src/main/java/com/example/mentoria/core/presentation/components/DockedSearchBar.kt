package com.example.mentoria.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mentoria.R
import com.example.mentoria.core.domain.model.Usuario

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DockedSearchBar(
    lista: List<Usuario>, // List<RegistroAcceso> / List<Usuario>
    onSearchItemSelected: (Usuario) -> Unit,
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

    DockedSearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = {
                    query = it
                },
                onSearch = { expanded = false },
                expanded = expanded,
                onExpandedChange = onExpandedChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Buscar...") },
                leadingIcon = {
                    if (expanded) {
                        Icon(
                            //painter =  painterResource(id = R.drawable.ic_arrow_back),
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Back button", //stringResource(id = R.string.back_button),
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
                            contentDescription = "Search", //stringResource(id = R.string.search),
                            //painter = painterResource(id = R.drawable.ic_search),
                            modifier = Modifier.padding(start = 16.dp),
                        )
                    }
                },
                trailingIcon = {
                    ProfileImage(
                        drawableResource = R.drawable.prueba_background, //R.drawable.avatar_6,
                        description = "Profile", // stringResource(id = R.string.profile),
                        modifier = Modifier
                            .padding(12.dp)
                            .size(32.dp),
                    )
                },
            )
        },
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        modifier = modifier,
        content = {
            if (searchResults.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    items(
                        items = searchResults,
                        key = { it.dni }
                    ) { usuario ->
                        ListItem(
                            headlineContent = { Text("${usuario.nombre} ${usuario.apellidos}") },
                            supportingContent = { Text(usuario.rol.toString()) },
                            leadingContent = {
                                ProfileImage(
                                    drawableResource = R.drawable.prueba_background, //email.sender.avatar,
                                    description = "Foto de perfil", // stringResource(id = R.string.profile),
                                    modifier = Modifier
                                        .size(32.dp),
                                )
                            },
                            modifier = Modifier.clickable {
                                onSearchItemSelected.invoke(usuario)
                                query = ""
                                expanded = false
                            },
                        )
                    }
                }
            } else if (query.isNotEmpty()) {
                Text(
                    text = "No se ha encontrado ningún resultado", // stringResource(id = R.string.no_item_found),
                    modifier = Modifier.padding(16.dp),
                )
            } else
                Text(
                    text = "No ha habido ninguna búsqueda", // stringResource(id = R.string.no_search_history),
                    modifier = Modifier.padding(16.dp),
                )
        },
    )
}

@Preview
@Composable
fun SearchBarPreview() {
    val lista = listOf(
        Usuario(
            dni = "12345678A",
            nombre = "Carolina",
            apellidos = "Sastre Garrido",
            rol = "ADMIN",
            password = "passw0rd",
            nfc = null
        ),
        Usuario(
            dni = "12345678B",
            nombre = "Manuela",
            apellidos = "Carmela",
            rol = "PROFESOR",
            password = "passw0rd",
            nfc = null
        )
    )
    DockedSearchBar(
        lista = lista,
        onSearchItemSelected = {}
    )
}