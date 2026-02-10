package com.example.mentoria.core.presentation.screens.horario

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.mentoria.core.domain.model.Horario
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import com.example.mentoria.core.presentation.components.MainTopAppBar
import com.example.mentoria.navigation.LocalOnNavigationBack
import java.time.LocalDate
import java.time.LocalTime
import java.time.temporal.ChronoUnit

// Constantes de diseño para la vista semanal
private val WEEK_HOUR_HEIGHT = 64.dp // Un poco más compacta que la vista diaria
private val SIDEBAR_WIDTH = 40.dp    // Ancho de la columna de horas
private val WEEK_START_HOUR = 7
private val WEEK_END_HOUR = 21

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HorarioScreen(
    modifier: Modifier = Modifier,
    usuario: Usuario,
    horarios: List<Horario>,
) {
    val diasSemana = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes")
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MainTopAppBar(
                title = "Horario ${usuario.curso}",
                onBack = LocalOnNavigationBack.current,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            // 1. Cabecera con los días de la semana (Fija, no hace scroll vertical)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = SIDEBAR_WIDTH) // Dejamos hueco para alinear con la parrilla
                    .height(40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                diasSemana.forEach { dia ->
                    Text(
                        text = dia.take(3), // Mostramos solo LUN, MAR, MIE...
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Divider()

            // 2. Zona de Scroll Vertical (Horas y Bloques)
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                // Calculamos el ancho de cada columna dinámicamente
                val screenWidth = maxWidth
                val columnWidth = (screenWidth - SIDEBAR_WIDTH) / 5

                // A. Columna de Horas (Izquierda)
                HoursSidebarWeekly(height = maxHeight) // Pasamos maxHeight aunque es scrollable para referencia

                // B. Parrilla de fondo (Líneas divisorias)
                GridBackground(
                    columnWidth = columnWidth,
                    totalHeight = maxHeight
                ) // Simplificado visualmente

                // C. Los bloques de las clases
                Box(
                    modifier = Modifier
                        .padding(start = SIDEBAR_WIDTH)
                        .fillMaxSize()
                ) {
                    horarios.forEach { horario ->
                        // Buscamos el índice del día (0=Lunes, 4=Viernes)
                        val dayIndex = getDayIndex(horario.diaSemana)

                        if (dayIndex != -1) {
                            WeeklyCourseCard(
                                horario = horario,
                                dayIndex = dayIndex,
                                columnWidth = columnWidth
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HoursSidebarWeekly(height: Dp) {
    Column(
        modifier = Modifier
            .width(SIDEBAR_WIDTH)
            .padding(top = 10.dp) // Ajuste fino para alinear texto con línea
    ) {
        for (hour in WEEK_START_HOUR..WEEK_END_HOUR) {
            Text(
                text = "$hour",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .height(WEEK_HOUR_HEIGHT)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun GridBackground(columnWidth: Dp, totalHeight: Dp) {
    // Dibujamos líneas horizontales para cada hora
    Column(modifier = Modifier.padding(start = SIDEBAR_WIDTH)) {
        for (hour in WEEK_START_HOUR..WEEK_END_HOUR) {
            Divider(
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f),
                modifier = Modifier
                    .height(WEEK_HOUR_HEIGHT)
                    .padding(top = 0.dp) // Truco visual: la altura define el espacio
            )
        }
    }

    // Opcional: Líneas verticales para separar días
    Row(modifier = Modifier.padding(start = SIDEBAR_WIDTH)) {
        repeat(5) {
            Box(
                modifier = Modifier
                    .width(columnWidth)
                    .height((WEEK_END_HOUR - WEEK_START_HOUR + 1) * WEEK_HOUR_HEIGHT.value.dp)
                    .border(
                        width = 0.5.dp,
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.1f)
                    )
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyCourseCard(
    horario: Horario,
    dayIndex: Int,
    columnWidth: Dp
) {
    // 1. Cálculos de posición Y (Vertical - Hora)
    val startMinFromBase = ChronoUnit.MINUTES.between(
        LocalTime.of(WEEK_START_HOUR, 0),
        horario.horaInicio
    )
    val durationMin = ChronoUnit.MINUTES.between(horario.horaInicio, horario.horaFin)

    val topOffsetDp = (startMinFromBase / 60f) * WEEK_HOUR_HEIGHT.value
    val heightDp = (durationMin / 60f) * WEEK_HOUR_HEIGHT.value

    // 2. Cálculos de posición X (Horizontal - Día)
    val leftOffsetDp = columnWidth * dayIndex

    // 3. Renderizado de la Tarjeta
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .width(columnWidth) // Ocupa exactamente el ancho de la columna
            .height(heightDp.dp)
            .absoluteOffset(
                x = leftOffsetDp,
                y = topOffsetDp.dp
            ) // Posición absoluta en la parrilla
            .padding(1.dp) // Pequeño margen blanco entre clases pegadas
            .clip(RoundedCornerShape(6.dp))
    ) {
        Column(
            modifier = Modifier.padding(4.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = horario.curso,
                style = MaterialTheme.typography.labelSmall, // Texto muy pequeño necesario
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 10.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            // En vista semanal a veces es mejor ocultar la hora exacta si no cabe
            if (heightDp > 40) { // Solo mostramos hora si el bloque es grande
                Text(
                    text = "${horario.horaInicio}",
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 8.sp,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                )
            }
        }
    }
}

// Función auxiliar para mapear días a columnas (0-4)
fun getDayIndex(dia: String): Int {
    return when (dia.lowercase().trim()) {
        "lunes", "mon" -> 0
        "martes", "tue" -> 1
        "miércoles", "miercoles", "wed" -> 2
        "jueves", "thu" -> 3
        "viernes", "fri" -> 4
        else -> -1 // Fin de semana o error
    }
}

// --- PREVIEW ---
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun HorarioSemanalPreview() {
    val sampleData = listOf(
        Horario("1", "Mates", "Lunes", LocalTime.of(8, 0), LocalTime.of(9, 30)),
        Horario("2", "Física", "Martes", LocalTime.of(9, 0), LocalTime.of(11, 0)),
        Horario("3", "Historia", "Miércoles", LocalTime.of(11, 0), LocalTime.of(12, 0)),
        Horario("4", "Kotlin", "Jueves", LocalTime.of(15, 0), LocalTime.of(17, 0)),
        Horario("5", "Inglés", "Viernes", LocalTime.of(8, 0), LocalTime.of(10, 0)),
        Horario("6", "Deporte", "Viernes", LocalTime.of(10, 0), LocalTime.of(11, 30))
    )
    MaterialTheme {
        HorarioScreen(
            usuario = Usuario(
                id = "1",
                dni = "12345678A",
                nombre = "Carolina",
                apellidos = "Sastre Garrido",
                rol = Rol.ALUMNO,
                nfc = null,
                fechaNacimiento = LocalDate.now(),
                gmail = "carolina@gmail.com",
                baja = false,
                curso = "7DMT",
                departamento = null
            ),
            horarios = sampleData
        )
    }
}