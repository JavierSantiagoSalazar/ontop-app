package com.example.ontopapp.data.datasource

import com.example.ontopapp.domain.Error
import com.example.ontopapp.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {
    val movies: Flow<List<Movie>>

    suspend fun isEmpty(): Boolean
    fun findById(id: Int): Flow<Movie>
    suspend fun save(movies: List<Movie>): Error?
}