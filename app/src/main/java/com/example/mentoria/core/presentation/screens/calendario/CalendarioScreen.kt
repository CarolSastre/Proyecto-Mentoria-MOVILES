
package com.example.mentoria.core.presentation.screens.calendario

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mentoria.core.data.MockDataProvider
import com.example.mentoria.core.domain.model.RegistroAcceso
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.presentation.components.CalendarioMensual
import com.example.mentoria.core.presentation.components.MainTopAppBar
import com.example.mentoria.core.presentation.components.RegistroDetailsCard
import com.example.mentoria.navigation.LocalOnNavigationBack
import java.time.LocalDate


@Composable
fun CalendarioScreen(
    modifier: Modifier = Modifier,
    registros: List<RegistroAcceso>,
    onBack: () -> Unit = LocalOnNavigationBack.current,
    onDateSelected: (LocalDate) -> Unit,
) {
    val startDate = LocalDate.now()
    val daySelected = rememberSaveable { mutableStateOf<LocalDate?>(startDate) }


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            MainTopAppBar(
                title = "Calendario",
                onBack = onBack,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            CalendarioMensual(
                modifier = Modifier,
                onDateSelected = {
                    daySelected.value = it
                }
            )
            Column (
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Registros del día ${daySelected.value}",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = modifier.padding(16.dp)
                )
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    items(
                        items = registros,
                        key = { it.id }
                    ) { registro ->
                        if (registro.fechaHora.toLocalDate() == daySelected.value)
                            RegistroDetailsCard(
                                registro = registro,
                                navigateToDetail = {},
                                toggleSelection = {},
                                onDeleteRegistro = {}
                            )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CalendarioPreview() {
    CalendarioScreen(
        registros = MockDataProvider.registros,
        onDateSelected = {}
    )
}