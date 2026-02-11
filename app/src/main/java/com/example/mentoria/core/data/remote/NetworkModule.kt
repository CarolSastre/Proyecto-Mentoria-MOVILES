package com.example.mentoria.core.data.remote

import com.example.mentoria.features.auth.data.remote.AuthApi
import com.example.mentoria.features.auth.data.remote.FakeUsuariosApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object NetworkModule {

    const val BASE_URL =
        "http://localhost:8080" // ""https://ktor-todos.fly.dev/" //  "http://192.168.1.8:8080/" //"https://my-json-server.typicode.com/rafapuig/PMDM-Android-Compose/"

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


    val apiService: MentoriaApiService by lazy {
        retrofit.create(MentoriaApiService::class.java)
    }

}


val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("http://localhost:8080") // TODO: poner direccion a la api
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}


