package com.example.ontopapp.usecases

import com.example.ontopapp.data.MoviesRepository
import com.example.ontopapp.domain.Error
import javax.inject.Inject

class RequestPopularMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(): Error? {
        return moviesRepository.requestPopularMovies()
    }
}
