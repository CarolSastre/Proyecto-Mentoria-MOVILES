package com.example.mentoria.core.data

import com.example.mentoria.core.domain.model.Departamento
import com.example.mentoria.core.domain.model.Horario
import com.example.mentoria.core.domain.model.RegistroAcceso
import com.example.mentoria.core.domain.model.Rol
import com.example.mentoria.core.domain.model.Usuario
import java.time.LocalDateTime
import java.time.LocalTime

object MockDataProvider {
    val departamentos = listOf(
        Departamento(id = "1", nombre = "Informática"),
        Departamento(id = "2", nombre = "Matemáticas")
    )

    val usuarios = listOf(
        Usuario(
            id = "user_0",
            dni = "admin",
            nombre = "admin",
            apellidos = "0",
            password = "admin",
            gmail = "admin@example.com",
            rol = Rol.ADMIN,
            fechaNacimiento = null,
            baja = false,
            curso = null,
            departamento = null,
            nfc = "NFC_admin",
            fotoPerfilUrl = null
        ),
        Usuario(
            id = "user_1",
            dni = "24446503X",
            nombre = "Carol",
            apellidos = "Sastre",
            password = "1234",
            gmail = "carol@example.com",
            rol = Rol.ALUMNO,
            fechaNacimiento = null,
            baja = false,
            curso = "7DMT",
            departamento = null,
            nfc = "NFC_CAROL",
            fotoPerfilUrl = null
        ),
        Usuario(
            id = "user_2",
            dni = "10000000P",
            nombre = "Profesor1",
            apellidos = "Docente1",
            password = "1234",
            gmail = "profe1@instituto.com",
            rol = Rol.PROFESOR,
            fechaNacimiento = null,
            baja = false,
            curso = null,
            departamento = null,
            nfc = "NFC_PROFE_1",
            fotoPerfilUrl = null
        ),
        Usuario(
            id = "user_3",
            dni = "20000001B",
            nombre = "Alumno1",
            apellidos = "Estudiente<1",
            password = "alumno",
            gmail = "alumno1@gmail.com",
            rol = Rol.ALUMNO,
            fechaNacimiento = null,
            baja = false,
            curso = "7DMT",
            departamento = null,
            nfc = "NFC_ALU_1",
            fotoPerfilUrl = null
        ),
    )

    val registros = listOf(
        RegistroAcceso(
            id = "reg_0",
            fechaHora = LocalDateTime.parse("2025-02-16T20:00:00"),
            accesoPermitido = true,
            mensaje = "Acceso permitido",
            usuario = usuarios[2]
        ),
        RegistroAcceso(
            id = "reg_1",
            fechaHora = LocalDateTime.parse("2025-02-16T20:45:00"),
            accesoPermitido = true,
            mensaje = "Acceso permitido",
            usuario = usuarios[2]
        ),
        RegistroAcceso(
            id = "reg_2",
            fechaHora = LocalDateTime.parse("2025-02-17T15:00:00"),
            accesoPermitido = true,
            mensaje = "Acceso permitido",
            usuario = usuarios[2]
        ),
        RegistroAcceso(
            id = "reg_3",
            fechaHora = LocalDateTime.parse("2025-02-17T20:30:00"),
            accesoPermitido = true,
            mensaje = "Acceso permitido",
            usuario = usuarios[2]
        ),
        RegistroAcceso(
            id = "reg_4",
            fechaHora = LocalDateTime.parse("2025-02-15T17:00:00"),
            accesoPermitido = true,
            mensaje = "Acceso permitido",
            usuario = usuarios[1]
        ),
        RegistroAcceso(
            id = "reg_5",
            fechaHora = LocalDateTime.parse("2025-02-16T20:00:00"),
            accesoPermitido = true,
            mensaje = "Acceso permitido",
            usuario = usuarios[1]
        ),
        RegistroAcceso(
            id = "reg_6",
            fechaHora = LocalDateTime.parse("2025-02-18T19:00:00"),
            accesoPermitido = false,
            mensaje = "Acceso denegado",
            usuario = usuarios[1]
        ),
    )

    val horarios = listOf(
        Horario("1", "Mates", "Lunes", LocalTime.of(8, 0), LocalTime.of(9, 30)),
        Horario("2", "Física", "Martes", LocalTime.of(9, 0), LocalTime.of(11, 0)),
        Horario("3", "Historia", "Miércoles", LocalTime.of(11, 0), LocalTime.of(12, 0)),
        Horario("4", "Kotlin", "Jueves", LocalTime.of(15, 0), LocalTime.of(17, 0)),
        Horario("5", "Inglés", "Viernes", LocalTime.of(8, 0), LocalTime.of(10, 0)),
        Horario("6", "Deporte", "Viernes", LocalTime.of(10, 0), LocalTime.of(11, 30))
    )
}