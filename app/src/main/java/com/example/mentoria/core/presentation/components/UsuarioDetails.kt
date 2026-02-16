package com.example.mentoria.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mentoria.R
import com.example.mentoria.core.domain.model.Usuario

@Composable
fun UsuarioDetails(
    modifier: Modifier = Modifier,
    usuario: Usuario?,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(R.drawable.prueba_background), // TODO: perfil
            contentDescription = "Perfil",
            modifier = modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Column(
            modifier = modifier
                .padding(start = 15.dp, bottom = 6.dp)
        ) {
            if (usuario?.id == null) {
                Text(
                    "Usuario invitado",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "ADMIN",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = modifier
                        .padding(start = 6.dp),
                    color = MaterialTheme.colorScheme.secondary
                )
            } else {
                Text(
                    "${usuario.nombre} ${usuario.apellidos}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    usuario.gmail,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = modifier
                        .padding(start = 6.dp),
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    usuario.dni,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = modifier
                        .padding(start = 6.dp),
                    color = MaterialTheme.colorScheme.secondary
                )
                var extra = "Administación"
                if (usuario.departamento != null) extra = usuario.departamento.nombre
                if (usuario.curso != null) extra = usuario.curso

                Text(
                    text = "${usuario.rol} - $extra",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = modifier
                        .padding(start = 6.dp),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}
