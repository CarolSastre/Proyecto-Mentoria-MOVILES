package com.example.mentoria.features.auth.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextOutOfTextField(
    text: String,
    title: String,
    placeholder: String = "Escribe aqui...",
    onValueChange: (String) -> Unit = {},
    onClearButton: () -> Unit = {}
) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        label = { Text(text = title) },
        placeholder = { Text(text = placeholder) },
        trailingIcon = {
            IconButton(
                onClick = onClearButton
            ) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Icon Clear"
                )
            }
        },
        shape = CircleShape
    )
}