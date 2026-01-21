package com.example.prexamen.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    onSearchClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onAboutClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text("Usuario")
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

            IconButton(
                onClick = { onBackClick() }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
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
                        text = { Text("Acerca de...") },
                        onClick = {
                            expanded = false
                            onAboutClick()
                        }
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun MainTopAppBarPreview() {
    MainTopAppBar()
}