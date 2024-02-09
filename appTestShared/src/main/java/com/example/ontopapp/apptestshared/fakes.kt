package com.example.ontopapp.apptestshared

import com.example.ontopapp.data.database.MovieDao
import com.example.ontopapp.data.database.localmodel.DbMovie
import com.example.ontopapp.data.server.MovieRemoteService
import com.example.ontopapp.data.server.remotemodel.MovieRemoteResult
import com.example.ontopapp.data.server.remotemodel.RemoteMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeMovieDao(movies: List<DbMovie> = emptyList()) : MovieDao {

    private val inMemoryMovies = MutableStateFlow(movies)
    private lateinit var findMovieFlow: MutableStateFlow<DbMovie>

    override fun findById(id: Int): Flow<DbMovie> {
        findMovieFlow = MutableStateFlow(inMemoryMovies.value.first { it.id == id })
        return findMovieFlow
    }

    override fun getAll(): Flow<List<DbMovie>> {
        return inMemoryMovies
    }

    override suspend fun movieCount(): Int = inMemoryMovies.value.size

    override suspend fun insertMovies(movies: List<DbMovie>) {
        inMemoryMovies.value = movies

        if (::findMovieFlow.isInitialized) {
            movies.firstOrNull { it.id == findMovieFlow.value.id }
                ?.let { findMovieFlow.value = it }
        }
    }
}

class FakeMovieRemoteService(private val movies: List<RemoteMovie> = emptyList()) :
    MovieRemoteService {

    override suspend fun listPopularMovies(apiKey: String) = MovieRemoteResult(
        page = 1,
        results = movies,
        totalPages = 1,
        totalResults = movies.size,
    )
}
