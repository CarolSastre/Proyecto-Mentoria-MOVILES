package com.example.mentoria.core.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mentoria.core.domain.model.Departamento
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import java.time.LocalDate

// TODO: creo que este componente no se usa
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsuarioDetailsBar(
    usuario: Usuario,
    isFullScreen: Boolean,
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface,
        ),
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = if (isFullScreen) Alignment.CenterHorizontally
                else Alignment.Start,
            ) {
                Text(
                    text = "${usuario.nombre} ${usuario.apellidos}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = "${usuario.rol}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.outline,
                )
            }
        },
        navigationIcon = {
            if (isFullScreen) {
                FilledIconButton(
                    onClick = onBack,
                    modifier = Modifier.padding(8.dp),
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                    ),
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "Arrow back",
                        modifier = Modifier.size(14.dp),
                    )
                }
            }
        },
        actions = {
            IconButton(
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = "More options",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        },
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun UsuariosDetailsPreview() {
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
    UsuarioDetailsBar(
        usuario = lista[0],
        isFullScreen = false,
        onBack = {}
    )
}