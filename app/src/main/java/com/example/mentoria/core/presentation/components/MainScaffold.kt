package com.example.mentoria.core.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Nfc
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mentoria.R
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.presentation.screens.home.HomeUiAction
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    modifier: Modifier = Modifier,
    usuario: Usuario?,
    title: String = "",
    snackBar: SnackbarHostState = remember { SnackbarHostState() },
    onAction: (HomeUiAction) -> Unit = {},
    onSearchClick: () -> Unit,
    /*
    onNFCClick: () -> Unit,
    onCalendarioClick: () -> Unit,
    onHorarioClick: () -> Unit,
    onLogOut: () -> Unit,
     */
    content: @Composable (PaddingValues) -> Unit
) {
    var toggleSearchBar by rememberSaveable { mutableStateOf(false) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val onExpandedChange: (Boolean) -> Unit = {
        onAction(HomeUiAction.OnSearchClick(it))
    }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))
                    UsuarioDetails(
                        usuario = usuario
                    )
                    HorizontalDivider()
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Registros",
                                style = MaterialTheme.typography.headlineSmall
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Schedule,
                                contentDescription = "Register"
                            )
                        },
                        modifier = modifier
                            .padding(horizontal = 8.dp),
                        selected = false,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                                /*TODO*/
                            }
                        }
                    )
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Horario",
                                style = MaterialTheme.typography.headlineSmall
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.School,
                                contentDescription = "Schedule"
                            )
                        },
                        modifier = modifier
                            .padding(horizontal = 8.dp),
                        selected = false,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                                onAction(HomeUiAction.OnHorarioClick)
                            }
                        }
                    )
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Calendario",
                                style = MaterialTheme.typography.headlineSmall
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.CalendarMonth,
                                contentDescription = "Calendar"
                            )
                        },
                        modifier = modifier
                            .padding(horizontal = 8.dp),
                        selected = false,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                                onAction(HomeUiAction.OnCalendarioClick)
                            }
                        }
                    )
                    HorizontalDivider()
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Cerrar sesión",
                                style = MaterialTheme.typography.headlineSmall
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Logout,
                                contentDescription = "LogOut"
                            )
                        },
                        modifier = modifier
                            .padding(horizontal = 8.dp),
                        selected = false,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                                onAction(HomeUiAction.OnLogOutClick)
                            }
                        }
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = snackBar,
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        onAction(HomeUiAction.ActivateNFC)
                    },
                    modifier = modifier
                        .padding(15.dp)
                        .size(70.dp),
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Nfc,
                        contentDescription = "NFC",
                        modifier = modifier.size(35.dp),
                    )
                }
            },
            topBar = {
                CenterAlignedTopAppBar(
                    modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    navigationIcon = {
                        IconButton(
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
                                description = "Perfil de ${usuario?.nombre} ${usuario?.apellidos}",
                                modifier = modifier
                                    .padding(start = 8.dp)
                                    .fillMaxSize()
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
                            onClick = {
                                //onExpandedChange(true)
                                onSearchClick()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Search"
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior,
                )
            },
            content = content
        )
    }
}

@Preview(
    showSystemUi = false, showBackground = false,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun MainScaffoldPreview() {
    MainScaffold(
        title = "prueba",
        onSearchClick = {},
        usuario = Usuario(
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
        )
    ) { }
}