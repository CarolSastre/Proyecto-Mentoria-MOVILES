package com.example.mentoria.core.presentation.screens.calendario

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mentoria.core.presentation.components.CalendarioMensual
import com.example.mentoria.core.presentation.components.MainTopAppBar
import com.example.mentoria.navigation.LocalOnNavigationBack
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarioScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = LocalOnNavigationBack.current,
    startDate: LocalDate = LocalDate.now(),
    onDateSelected: (LocalDate) -> Unit,
) {
    val daySelected = rememberSaveable { mutableStateOf<LocalDate?>(startDate) }

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            MainTopAppBar(
                title = "Calendario",
                onBack = onBack
            )
        }
    ) { innerPadding ->
        CalendarioMensual(
            modifier = Modifier
                .padding(innerPadding),
            /*onDateSelected = {
                // TODO: cambiar (creo que esto no furula así)
                daySelected.value = it
                onDateSelected(it)
            }*/
        ) { onDateSelected ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // TODO: mostrar los registros de ese día
                Text("Holaa")
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