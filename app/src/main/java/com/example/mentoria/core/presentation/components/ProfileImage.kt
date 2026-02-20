package com.example.mentoria.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ProfileImage(
    fotoPerfilUrl: String,
    //drawableResource: Int,
    description: String,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape),
        painter = painterResource(fotoPerfilUrl.hashCode()),
        //painter = painterResource(id = drawableResource),
        contentDescription = description,
        contentScale = ContentScale.FillWidth
    )
}