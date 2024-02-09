package com.example.ontopapp.data.database

import com.example.ontopapp.data.datasource.MoviesLocalDataSource
import com.example.ontopapp.data.tryCall
import com.example.ontopapp.domain.Error
import com.example.ontopapp.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.example.ontopapp.data.database.localmodel.DbMovie as DbMovie

class MovieLocalDataSourceImpl @Inject constructor(private val movieDao: MovieDao) :
    MoviesLocalDataSource {

    override val movies: Flow<List<Movie>> = movieDao.getAll().map { it.toDomainModel() }

    override suspend fun isEmpty(): Boolean = movieDao.movieCount() == 0

    override fun findById(id: Int): Flow<Movie> = movieDao.findById(id).map { it.toDomainModel() }

    override suspend fun save(movies: List<Movie>): Error? = tryCall {
        movieDao.insertMovies(movies.fromDomainModel())
    }.fold(
        ifLeft = { it },
        ifRight = { null },
    )
}

private fun List<DbMovie>.toDomainModel(): List<Movie> = map { it.toDomainModel() }

private fun DbMovie.toDomainModel(): Movie =
    Movie(
        id,
        title,
        overview,
        releaseDate,
        posterPath,
        backdropPath,
        originalLanguage,
        originalTitle,
        popularity,
        voteAverage,
    )

private fun List<Movie>.fromDomainModel(): List<DbMovie> = map { it.fromDomainModel() }

private fun Movie.fromDomainModel(): DbMovie = DbMovie(
    id,
    title,
    overview,
    releaseDate,
    posterPath,
    backdropPath,
    originalLanguage,
    originalTitle,
    popularity,
    voteAverage,
)
