package com.example.mentoria.core.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mentoria.R
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UsuarioDetailsCard(
    usuario: Usuario,
    navigateToDetail: (String) -> Unit,
    toggleSelection: (String) -> Unit,
    modifier: Modifier = Modifier,
    isOpened: Boolean = false,
    isSelected: Boolean = false,
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .semantics { selected = isSelected }
            .clip(CardDefaults.shape)
            .combinedClickable(
                onClick = { navigateToDetail(usuario.dni) },
                onLongClick = { toggleSelection(usuario.dni) },
            )
            .clip(CardDefaults.shape),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer
            else if (isOpened) MaterialTheme.colorScheme.secondaryContainer
            else MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                val clickModifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ) { toggleSelection(usuario.dni) }
                AnimatedContent(targetState = isSelected, label = "avatar") {
                    if (it) {
                        SelectedProfileImage(clickModifier)
                    } else {
                        ProfileImage(
                            drawableResource = R.drawable.prueba_background,//usuario.avatar,
                            description = "${usuario.nombre} ${usuario.apellidos}",
                            clickModifier,
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "${usuario.nombre} ${usuario.apellidos}",
                        style = MaterialTheme.typography.labelMedium,
                    )
                    Text(
                        text = usuario.rol.toString(),
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceContainerHigh),
                ) {
                    Icon(
                        imageVector = Icons.Outlined.StarOutline,
                        contentDescription = "Favorite",
                        tint = MaterialTheme.colorScheme.outline,
                    )
                }
            }

            Text(
                text = "Subject",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
            )
            Text(
                text = "Body",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun SelectedProfileImage(modifier: Modifier = Modifier) {
    Box(
        modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary),
    ) {
        Icon(
            imageVector = Icons.Outlined.Check,
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.Center),
            tint = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Preview
@Composable
fun UsuarioDetailsCardPreview() {
    val lista = listOf(
        Usuario(
            dni = "12345678A",
            nombre = "Carolina",
            apellidos = "Sastre Garrido",
            rol = Rol.ADMIN,
            password = "passw0rd",
            nfc = null
        ),
        Usuario(
            dni = "12345678B",
            nombre = "Manuela",
            apellidos = "Carmela",
            rol = Rol.PROFESOR,
            password = "passw0rd",
            nfc = null
        )
    )
    UsuarioDetailsCard(
        usuario = lista[0],
        navigateToDetail = {},
        toggleSelection = {}
    )
}