
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Nfc
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.material3.NavigationDrawerItemDefaults
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
import androidx.compose.ui.graphics.Color
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
    toggle: Boolean,
    content: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                // OxfordBlue para el fondo del menú lateral
                drawerContainerColor = MaterialTheme.colorScheme.surface,
                drawerContentColor = MaterialTheme.colorScheme.onSurface,
                drawerShape = RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp)
            ) {
                Column(
                    modifier = modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))
                    UsuarioDetails(
                        usuario = usuario
                    )
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))

                    val drawerItemColors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = MaterialTheme.colorScheme.secondary,
                        selectedIconColor = MaterialTheme.colorScheme.secondary,
                        selectedTextColor = MaterialTheme.colorScheme.primaryContainer,
                        unselectedContainerColor = Color.Transparent,
                        unselectedIconColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f),
                        unselectedTextColor = MaterialTheme.colorScheme.onSurface
                    )

                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Asistencias",
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
                        },
                        colors = drawerItemColors,
                    )
                    if (usuario?.rol != Rol.ALUMNO) {
                        NavigationDrawerItem(
                            label = {
                                Text(
                                    text = "Lista de alumnos",
                                    style = MaterialTheme.typography.headlineSmall
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Filled.FormatListNumbered,
                                    contentDescription = "List"
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
                            },
                            colors = drawerItemColors,
                        )
                        NavigationDrawerItem(
                            label = {
                                Text(
                                    text = "Crear alumno",
                                    style = MaterialTheme.typography.headlineSmall
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Filled.People,
                                    contentDescription = "Student"
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
                            },
                            colors = drawerItemColors,
                        )
                    }
                    if (usuario?.rol == Rol.ADMIN) {
                        NavigationDrawerItem(
                            label = {
                                Text(
                                    text = "Lista de profesores",
                                    style = MaterialTheme.typography.headlineSmall
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Filled.FormatListNumbered,
                                    contentDescription = "List"
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
                            },
                            colors = drawerItemColors,
                        )
                        NavigationDrawerItem(
                            label = {
                                Text(
                                    text = "Crear alumno",
                                    style = MaterialTheme.typography.headlineSmall
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Filled.People,
                                    contentDescription = "Teacher"
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
                            },
                            colors = drawerItemColors,
                        )
                    }
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
                        },
                        colors = drawerItemColors,
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
                        },
                        colors = drawerItemColors,
                    )
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
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
                        },
                        colors = drawerItemColors,
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
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
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
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onBackground,
                        navigationIconContentColor = MaterialTheme.colorScheme.background,
                        actionIconContentColor = MaterialTheme.colorScheme.background
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
                            if (usuario?.fotoPerfilUrl != null) {
                                ProfileImage(
                                    fotoPerfilUrl = usuario.fotoPerfilUrl,
                                    description = "Foto de perfil",
                                    modifier = Modifier.fillMaxSize(),
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = null,
                                    modifier = Modifier.size(60.dp),
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    },
                    title = {
                        Text(
                            text = title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    },
                    actions = {
                        if (!toggle) {
                            IconButton(
                                onClick = {
                                    onSearchClick()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "Search",
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        } else {
                            IconButton(
                                onClick = {
                                    onSearchClick()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBackIosNew,
                                    contentDescription = "ArrowBack",
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
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
    showSystemUi = true,
    showBackground = true
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
            departamento = null,
            fotoPerfilUrl = null,
            password = ""
        ),
        toggle = false,
        onAction = {}
    ) { }
}