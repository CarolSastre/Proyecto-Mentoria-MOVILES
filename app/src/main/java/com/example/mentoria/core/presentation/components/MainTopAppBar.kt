package com.example.mentoria.core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mentoria.R
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    usuario: Usuario,
    onSearchClick: () -> Unit,
    onSettingsClick: () -> Unit,
    backButton: Boolean = false,
    onBackClick: () -> Unit?,
    //onLogOut: () -> Unit,
    scope: CoroutineScope,
    drawerState: DrawerState,
) {
    var expanded by remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    //val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    //val scope = rememberCoroutineScope()

    CenterAlignedTopAppBar(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        navigationIcon = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                },
            ) {
                ProfileImage(
                    drawableResource = R.drawable.prueba_background,
                    description = "${usuario.nombre} ${usuario.apellidos}",
                    modifier = modifier.padding(start = 8.dp)
                )
            }
        },
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            IconButton(
                onClick = { onSearchClick() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search"
                )
            }

            Box {
                IconButton(
                    onClick = { expanded = true }
                ) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "Search"
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Ajustes...") },
                        onClick = {
                            expanded = false
                            onSettingsClick()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Cerrar sesión") },
                        onClick = {
                            expanded = false
                            //onLogOut()
                        }
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior,
    )
}

@Preview
@Composable
fun MainTopAppBarPreview() {
    MainTopAppBar(
        title = "prueba",
        usuario = Usuario(
            dni = "12345678A",
            nombre = "Carolina",
            apellidos = "Sastre Garrido",
            rol = Rol.ADMIN,
            password = "passw0rd",
            nfc = null
        ),
        onSearchClick = {},
        onSettingsClick = {},
        backButton = true,
        onBackClick = {},
        //onLogOut = {},
        scope = rememberCoroutineScope(),
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )
}