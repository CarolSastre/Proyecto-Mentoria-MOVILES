package com.example.mentoria.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mentoria.R
//import coil.compose.AsyncImage
import com.example.mentoria.core.domain.model.Usuario

@Composable
fun ProfileHeader(usuario: Usuario?) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Avatar
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onSurfaceVariant)
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

        Spacer(modifier = Modifier.height(16.dp))

        // Nombre
        Text(
            text = "${usuario?.nombre} ${usuario?.apellidos}",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Chip del Rol
        AssistChip(
            onClick = { },
            label = { Text(usuario?.rol.toString()) },
            leadingIcon = {
                Icon(Icons.Default.VerifiedUser, contentDescription = null, modifier = Modifier.size(16.dp))
            }
        )

        // Indicador de Baja
        if (usuario?.baja != null && usuario.baja) {
            Spacer(modifier = Modifier.height(4.dp))
            Badge(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            ) {
                Text("USUARIO DE BAJA", modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp))
            }
        }
    }
}