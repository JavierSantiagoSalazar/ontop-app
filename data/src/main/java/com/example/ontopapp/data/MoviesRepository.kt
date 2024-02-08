package com.example.ontopapp.data

import com.example.ontopapp.data.datasource.MoviesLocalDataSource
import com.example.ontopapp.data.datasource.MoviesRemoteDataSource
import com.example.ontopapp.domain.Error
import com.example.ontopapp.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val localDataSource: MoviesLocalDataSource,
    private val remoteDataSource: MoviesRemoteDataSource,
) {
    val popularMovies get() = localDataSource.movies

    fun findById(id: Int): Flow<Movie> = localDataSource.findById(id)

    suspend fun requestPopularMovies(): Error? {
        if (localDataSource.isEmpty()) {
            val movies = remoteDataSource.findPopularMovies()
            movies.fold(ifLeft = { return it }) {
                localDataSource.save(it)
            }
        }
        return null
    }
}
