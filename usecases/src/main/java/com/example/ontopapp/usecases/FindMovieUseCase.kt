package com.example.ontopapp.usecases

import com.example.ontopapp.data.MoviesRepository
import com.example.ontopapp.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindMovieUseCase @Inject constructor(private val repository: MoviesRepository) {

    operator fun invoke(id: Int): Flow<Movie> = repository.findById(id)
}
