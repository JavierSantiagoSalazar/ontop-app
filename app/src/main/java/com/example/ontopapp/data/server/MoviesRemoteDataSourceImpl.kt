package com.example.ontopapp.data.server

import arrow.core.Either
import com.example.ontopapp.BuildConfig
import com.example.ontopapp.data.datasource.MoviesRemoteDataSource
import com.example.ontopapp.data.server.remotemodel.RemoteMovie
import com.example.ontopapp.data.tryCall
import com.example.ontopapp.domain.Error
import com.example.ontopapp.domain.models.Movie
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val movieRemoteService: MovieRemoteService,
) : MoviesRemoteDataSource {

    override suspend fun findPopularMovies(): Either<Error, List<Movie>> = tryCall {
        movieRemoteService
            .listPopularMovies(BuildConfig.API_KEY)
            .results
            .toDomainModel()
    }
}

private fun List<RemoteMovie>.toDomainModel(): List<Movie> = map { it.toDomainModel() }

private fun RemoteMovie.toDomainModel(): Movie =
    Movie(
        id = id,
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        posterPath = "https://image.tmdb.org/t/p/w185/$posterPath",
        backdropPath = backdropPath?.let { "https://image.tmdb.org/t/p/w780/$it" } ?: "",
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        popularity = popularity,
        voteAverage = voteAverage,
    )
