package com.example.mentoria.core.presentation.screens.usuariodetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.presentation.components.InfoRow
import com.example.mentoria.core.presentation.components.InfoSectionCard
import com.example.mentoria.core.presentation.components.MainTopAppBar
import com.example.mentoria.navigation.LocalOnNavigationBack
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import com.example.mentoria.core.presentation.components.ProfileHeader
import java.time.LocalDate

@Composable
fun UsuarioDetailsScreen(
    modifier: Modifier = Modifier,
    usuario: Usuario?,
    onAction: (UsuarioDetailsAction) -> Unit,
    onBack: () -> Unit = LocalOnNavigationBack.current,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MainTopAppBar(
                title = "Detalles de Usuario", // Título genérico, el nombre va en el contenido
                onBack = onBack,
                // Opcional: Agregar botón de editar en la TopBar
                /*
                actions = {
                    IconButton(onClick = { onAction(UsuarioDetailsAction.OnEditClick) }) {
                        Icon(Icons.Default.Edit, contentDescription = "Editar")
                    }
                }
                 */
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProfileHeader(usuario)

            InfoSectionCard(title = "Información Personal") {
                InfoRow(icon = Icons.Default.Badge, label = "DNI", value = usuario?.dni)
                InfoRow(icon = Icons.Default.Email, label = "Email", value = usuario?.gmail)

                usuario?.fechaNacimiento?.let { date ->
                    val formattedDate =
                        date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
                    InfoRow(icon = Icons.Default.Cake, label = "Nacimiento", value = formattedDate)
                }

                usuario?.nfc?.let { nfc ->
                    InfoRow(icon = Icons.Default.Nfc, label = "NFC ID", value = nfc)
                }
            }

            if (usuario?.curso != null || usuario?.departamento != null) {
                InfoSectionCard(title = "Académico / Laboral") {
                    usuario.curso?.let { curso ->
                        InfoRow(icon = Icons.Default.School, label = "Curso", value = curso)
                    }
                    usuario.departamento?.let { dep ->
                        InfoRow(
                            icon = Icons.Default.Work,
                            label = "Departamento",
                            value = dep.nombre
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun UsuarioDetailsScreenPreview() {
    UsuarioDetailsScreen(
        usuario = Usuario(
            id = "1",
            dni = "12345678A",
            nombre = "Carolina",
            apellidos = "Sastre Garrido",
            rol = Rol.ALUMNO,
            nfc = null,
            fechaNacimiento = LocalDate.parse("2001-06-12"),
            gmail = "carolina@gmail.com",
            baja = false,
            curso = "7DMT",
            departamento = null,
            fotoPerfilUrl = null,
            password = ""
        ),
        onAction = {}
    )
}