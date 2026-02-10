package com.example.mentoria.core.presentation.screens.calendario

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mentoria.core.domain.model.RegistroAcceso
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.presentation.components.CalendarioMensual
import com.example.mentoria.core.presentation.components.MainTopAppBar
import com.example.mentoria.core.presentation.components.RegistroDetailsCard
import com.example.mentoria.navigation.LocalOnNavigationBack
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarioScreen(
    modifier: Modifier = Modifier,
    startDate: LocalDate = LocalDate.now(),
    onBack: () -> Unit = LocalOnNavigationBack.current,
    onDateSelected: (LocalDate) -> Unit,
) {
    val daySelected = rememberSaveable { mutableStateOf<LocalDate?>(startDate) }
    //val registros = rememberSaveable { mutableStateOf<List<RegistroAcceso>>(emptyList()) }
    val usu = Usuario(
        id = "1",
        dni = "12345678A",
        nombre = "Carolina",
        apellidos = "Sastre Garrido",
        rol = Rol.ALUMNO,
        password = "passw0rd",
        nfc = null,
        fechaNacimiento = LocalDate.now(),
        gmail = "carolina@gmail.com",
        baja = false,
        curso = "7DMT",
        departamento = null
    )

    val registros = rememberSaveable { mutableStateOf<List<RegistroAcceso>>(listOf(
        RegistroAcceso(
            id = "1",
            fechaHora = LocalDate.now().atStartOfDay(),
            accesoPermitido = true,
            mensaje = "Acceso permitido",
            usuario = usu
        ),RegistroAcceso(
            id = "2",
            fechaHora = LocalDate.now().atTime(15, 0),
            accesoPermitido = true,
            mensaje = "Acceso permitido",
            usuario = usu
        ),RegistroAcceso(
            id = "3",
            fechaHora = LocalDate.parse("2026-02-07").atTime(15, 0),
            accesoPermitido = true,
            mensaje = "Acceso permitido",
            usuario = usu
        ),RegistroAcceso(
            id = "4",
            fechaHora = LocalDate.parse("2026-02-06").atTime(15, 0),
            accesoPermitido = false,
            mensaje = null,
            usuario = usu
        ),
    )) }

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            MainTopAppBar(
                title = "Calendario",
                onBack = onBack,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize(),
        ) {
            CalendarioMensual(
                modifier = modifier
                    .padding(innerPadding),
                onDateSelected = {
                    daySelected.value = it
                }
            )
            Column (
                modifier = modifier
                    .fillMaxSize()
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
                        items = registros.value,
                        key = { it.id }
                    ) { registro ->
                        // TODO: esto debería funcionar sin el if
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun CalendarioPreview() {
    CalendarioScreen(
        onDateSelected = {}
    )
}