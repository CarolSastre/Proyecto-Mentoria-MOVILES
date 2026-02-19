package com.example.mentoria.core.presentation.screens.horario

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mentoria.core.domain.model.Horario
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

// Constantes de diseño
val HOUR_HEIGHT = 80.dp // Altura visual de 1 hora en la pantalla
val START_HOUR = 7 // El calendario empieza a las 7:00
val END_HOUR = 21 // El calendario termina a las 21:00


@Composable
fun HorarioDiaScreen(
    horarios: List<Horario>,
    modifier: Modifier = Modifier
) {
    // Filtramos o aseguramos que solo mostramos un día (opcional según tu lógica)
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Cabecera
        Text(
            text = "Mi Horario: ${horarios.firstOrNull()?.diaSemana ?: "Hoy"}",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        // Área del Calendario con Scroll
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(end = 16.dp, bottom = 16.dp) // Padding para que no se corte
        ) {
            // 1. Dibujar las líneas de las horas (Fondo)
            HoursSidebar()

            // 2. Dibujar los bloques de las clases (Superpuestos)
            Box(
                modifier = Modifier
                    .padding(start = 60.dp) // Espacio para dejar ver la hora a la izquierda
                    .fillMaxWidth()
            ) {
                horarios.forEach { horario ->
                    CourseCard(horario = horario)
                }
            }
        }
    }
}


@Composable
fun HoursSidebar() {
    Column(modifier = Modifier.fillMaxWidth()) {
        for (hour in START_HOUR..END_HOUR) {
            Row(
                modifier = Modifier
                    .height(HOUR_HEIGHT)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                // Etiqueta de la hora (ej: 09:00)
                Text(
                    text = String.format("%02d:00", hour),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .width(50.dp)
                        .padding(start = 8.dp, top = 2.dp)
                )
                // Línea divisoria horizontal
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.outlineVariant,
                    modifier = Modifier
                        .padding(top = 10.dp) // Alinear visualmente con el texto
                        .alpha(0.5f)
                )
            }
        }
    }
}


@Composable
fun CourseCard(horario: Horario) {
    // Cálculos de posición y tamaño
    val durationMin = ChronoUnit.MINUTES.between(horario.horaInicio, horario.horaFin)
    val startMinFromBase = ChronoUnit.MINUTES.between(
        LocalTime.of(START_HOUR, 0),
        horario.horaInicio
    )

    // Convertir minutos a DP
    val topOffsetDp = (startMinFromBase / 60f) * HOUR_HEIGHT.value
    val heightDp = (durationMin / 60f) * HOUR_HEIGHT.value

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .offset(y = topOffsetDp.dp) // Desplazamiento vertical mágico
            .height(heightDp.dp)         // Altura según duración
            .padding(bottom = 2.dp)      // Pequeño margen entre bloques pegados
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Text(
                text = horario.curso,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${formatoHora(horario.horaInicio)} - ${formatoHora(horario.horaFin)}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
            )
        }
    }
}

// Función auxiliar para formatear hora

fun formatoHora(time: LocalTime): String {
    return time.format(DateTimeFormatter.ofPattern("HH:mm"))
}

// --- PREVIEW ---

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HorarioPreview() {
    val sampleData = listOf(
        Horario("1", "Matemáticas", "Lunes", LocalTime.of(8, 0), LocalTime.of(9, 30)),
        Horario("2", "Física Aplicada", "Lunes", LocalTime.of(10, 0), LocalTime.of(12, 0)),
        Horario("3", "Recreo", "Lunes", LocalTime.of(12, 0), LocalTime.of(12, 45)),
        Horario("4", "Programación Kotlin", "Lunes", LocalTime.of(13, 0), LocalTime.of(15, 0))
    )
    MaterialTheme {
        HorarioDiaScreen(horarios = sampleData)
    }
}