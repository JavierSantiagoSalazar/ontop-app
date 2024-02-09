package com.example.ontopapp.apptestshared

import com.example.ontopapp.data.database.localmodel.DbMovie
import com.example.ontopapp.data.server.remotemodel.RemoteMovie

fun buildDatabaseMovies(vararg id: Int) = id.map {
    DbMovie(
        id = it,
        title = "Title $it",
        overview = "Overview $it",
        releaseDate = "01/01/2025",
        posterPath = "",
        backdropPath = "",
        originalLanguage = "EN",
        originalTitle = "Original Title $it",
        popularity = 5.0,
        voteAverage = 5.1,
    )
}

fun buildRemoteMovies(vararg id: Int) = id.map {
    RemoteMovie(
        adult = false,
        backdropPath = "",
        genreIds = listOf(28, 35),
        id = it,
        originalLanguage = "EN",
        originalTitle = "Original Title $it",
        overview = "Overview $it",
        popularity = 5.0,
        posterPath = "",
        releaseDate = "01/01/2025",
        title = "Title $it",
        video = false,
        voteAverage = 5.1,
        voteCount = 10,
    )
}
