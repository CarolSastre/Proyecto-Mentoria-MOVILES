package com.example.mentoria.core.data.remote

import com.example.mentoria.core.data.remote.dto.UsuarioResponse

class MentoriaApiServiceImpl(
    // TODO: en este ejemplo rafa utiliza ktor, pero habría que usar retrofit?
    //private val client: HttpClient
) : MentoriaApiService {
    override suspend fun fetchUsuarios(
        page: Int,
        region: String?
    ): UsuarioResponse {
        TODO("Not yet implemented")
    }

    /* Dos ejemplos: un get y una query
    override suspend fun fetchPopularMovies(
        page: Int,
        region: String?
    ): MoviesResponse = client
        .get(POPULAR_MOVIES_ENDPOINT) {
            //parameter("api_key", BuildConfig.API_KEY)
            //parameter("language", "es-ES")
            parameter("page", page)
            if(region != null) parameter("region", region) // region?.let{ parameter("region", it) }
        }.body()

        override suspend fun searchMovies(
        query: String,
        page: Int
    ): MoviesResponse {
        return client.get(SEARCH_MOVIES_ENDPOINT) {
            parameter("query", query)
            parameter("page", page)
        }.body()
    }
    */

    override suspend fun fetchProfesores(
        page: Int,
        region: String?
    ): UsuarioResponse {
        TODO("Not yet implemented")
    }

    override suspend fun searchUsuario(
        query: String,
        page: Int
    ): UsuarioResponse {
        TODO("Not yet implemented")
    }

    companion object Factory {
        fun create(): MentoriaApiService {
            return MentoriaApiServiceImpl(
                // de Gemini
                //client = HttpClient(Android)
                // de rafa (con ktor)
                // client = KtorClientProvider.client
            )
        }
    }
}