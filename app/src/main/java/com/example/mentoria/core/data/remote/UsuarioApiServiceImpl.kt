package com.example.mentoria.core.data.remote

import com.example.mentoria.core.data.remote.dto.UsuarioDto
import kotlinx.coroutines.flow.Flow

class UsuarioApiServiceImpl(
    // TODO: en este ejemplo rafa utiliza ktor, pero habría que usar retrofit?
    //private val client: HttpClient
) : UsuarioApiService {

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

    override fun getAllUsuarios(): Flow<List<UsuarioDto>> {
        TODO("Not yet implemented")
    }

    override fun getUsuarioById(id: Int): Flow<UsuarioDto> {
        TODO("Not yet implemented")
    }
}