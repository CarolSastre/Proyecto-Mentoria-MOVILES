package com.example.mentoria.di

import com.example.mentoria.core.data.remote.UsuarioApiService
import com.example.mentoria.features.auth.data.remote.AuthInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

// TODO: seguramente haya que borrar el object
/*
object NetworkModule {

    const val BASE_URL =
        "http://192.168.56.1:8080" // ""https://ktor-todos.fly.dev/" //  "http://192.168.1.8:8080/" //"https://my-json-server.typicode.com/rafapuig/PMDM-Android-Compose/"

    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
        isLenient = true
    }

    private val contentType = "application/json;charset=UTF-8".toMediaType()


    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()


    val apiService: UsuarioApiService by lazy {
        retrofit.create(UsuarioApiService::class.java)
    }

}
*/

val networkModule = module {
    // 1. configuración JSON
    single {
        Json {
            ignoreUnknownKeys = true
            explicitNulls = false
            isLenient = true
        }
    }

    // 2. Interceptor
    singleOf(::AuthInterceptor)

    // 3. OkHttpClient
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<AuthInterceptor>())
            .build()
    }

    // 4. Instancia de retrofit
    single {
        // IMPORTANTE:
        // Usa "http://10.0.2.2:8080/" si usas el Emulador de Android.
        // Usa "http://TU_IP_LOCAL:8080/" si usas un móvil físico. // ? 10.161.63.163
        val baseUrl = "http://10.161.63.163:8080/"
        val contentType = "application/json".toMediaType()

        Retrofit.Builder()
            .baseUrl(baseUrl) // TODO: poner direccion a la api
            .client(get()).addConverterFactory(get<Json>().asConverterFactory(contentType))
            .build()
    }
}


