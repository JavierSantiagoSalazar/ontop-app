package com.example.ontopapp.data.datasource

import arrow.core.Either
import com.example.ontopapp.domain.Error
import com.example.ontopapp.domain.models.Movie

interface MoviesRemoteDataSource {
    suspend fun findPopularMovies(): Either<Error, List<Movie>>
}
