# Mentoría APP

Mentoría APP es una aplicación nativa para Android diseñada para la gestión de mentorías, usuarios, horarios y registros de acceso. Desarrollada completamente con **Jetpack Compose** y siguiendo los principios de **Clean Architecture**, la app asegura una base de código escalable, mantenible y testeable.

## Características Principales

* **Autenticación Segura:** Sistema de Login y Registro gestionado mediante JWT.
* **Gestión de Sesiones (DataStore):** Mantenimiento persistente de la sesión del usuario (Token e ID).
* **Perfiles de Usuario:** Visualización y gestión de datos de los usuarios (Estudiantes, Mentores, Administradores).
* **Calendario y Horarios:** Consulta de horarios de mentoría y calendario mensual interactivo.
* **Registro de Accesos:** Historial y control de los registros de entrada/salida.
* **Búsqueda Avanzada:** Directorio de usuarios con barra de búsqueda integrada.

## Especificaciones

La aplicación está construida utilizando el prototipo de arquitectura moderno de Android:

* **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) - UI declarativa nativa.
* **Navegación:** `androidx-navigation3-compose` (última versión Alpha) para una navegación estructurada y segura en Compose.
* **Inyección de Dependencias:** [Koin](https://insert-koin.io/) - Framework ligero y pragmático para Kotlin, utilizando la sintaxis DSL moderna (`singleOf`, `viewModelOf`).
* **Red (Networking):**
    * [Retrofit](https://square.github.io/retrofit/) para el consumo de la API REST.
    * [OkHttp3](https://square.github.io/okhttp/) con interceptores personalizados para inyectar el Token de Autorización.
    * [Kotlinx Serialization](https://kotlinlang.org/docs/serialization.html) para el parseo de JSON (`@Serializable`).
* **Persistencia Local:**
    * **DataStore (Preferences):** Para guardar datos ligeros y críticos de la sesión (Token JWT y UserId).
    * **Room Database:** (Configurado) Para caché local y persistencia estructurada de entidades (Usuarios, Horarios, etc.).
* **Programación Asíncrona:** Corrutinas de Kotlin (`Flow`, `StateFlow`) para un flujo de datos reactivo y eficiente desde la capa de datos hasta la UI.

## Clean Architecture

El proyecto está organizado estrictamente siguiendo **Clean Architecture**, separando las responsabilidades en capas claramente definidas:

### 1. Capa de Presentación (Presentation)
Contiene la UI (Pantallas en Compose), los ViewModels y la gestión del estado (UI State y UI Events).
* **Ejemplo:** `HomeViewModel`, `LoginScreen`, `MainTopAppBar`.

### 2. Capa de Dominio (Domain)
El corazón de la aplicación. Contiene las reglas de negocio, modelos de datos puros de Kotlin y los casos de uso (Use Cases). No tiene dependencias de Android.
* **Ejemplo:** `Usuario.kt` (Modelo), `LoginUseCase`, `AuthRepository` (Interfaz).

### 3. Capa de Datos (Data)
Implementa los repositorios y se encarga de obtener los datos de fuentes externas o locales.
* **Ejemplo:** `AuthRepositoryImpl`, `UsuarioApiService` (Remote), `SessionManager` (Local/DataStore), `AppDatabase` (Local/Room).

### Inyección de Dependencias (DI)
La aplicación está divida en tres módulos principales que crean los Singletons correspondientes:
* `NetworkModule.kt`. Para la conexión con la API.
* `AuthModule.kt`. Para el manejo de la autenticación y sesiones.
* `AppModule.kt`. Para la implementación de la arquitectura y capas.

## Configuración y Ejecución

### Prerrequisitos
* [Android Studio](https://developer.android.com/studio) (Ladybug o superior recomendado).
* JDK 17 o superior.

### Entorno de Red
La app está configurada para conectar con un backend local. La dirección IP base está definida en el módulo de Koin (`NetworkModule.kt`).
* **Si usas el Emulador de Android:** La URL debe ser `http://10.0.2.2:8080/`.
* **Si usas un Dispositivo Físico:** Debes cambiar la URL por la IP local de tu ordenador en la red WiFi (ej: `http://192.168.1.XX:8080/`).

### Pasos para ejecutar
1.  Clona el repositorio: `git clone https://github.com/carolsastre/mentoria-app.git`
2.  Abre el proyecto en Android Studio.
3.  Sincroniza el proyecto con Gradle.
4.  Revisa/Modifica la Base URL en la configuración de Retrofit según tu entorno (ver sección anterior).
5.  Pulsa `Run` (▶️) para compilar e instalar en tu emulador o dispositivo físico.

## Próximos Pasos
* Implementar la persistencia y caché de Usuarios mediante Room.
* Migrar llamadas *Fake* restantes de la API a endpoints reales del Backend.
* Añadir Test Unitarios para los Casos de Uso y ViewModels del paquete core.
* Refinar el diseño visual (UI/UX) general aplicando el sistema de Material Design 3.
