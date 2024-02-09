package com.example.ontopapp.usecases

import com.example.ontopapp.data.MoviesRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val repository: MoviesRepository) {

    operator fun invoke() = repository.popularMovies
}
