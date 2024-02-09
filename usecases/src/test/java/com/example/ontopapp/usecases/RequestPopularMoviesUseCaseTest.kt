package com.example.ontopapp.usecases

import com.example.ontopapp.data.MoviesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class RequestPopularMoviesUseCaseTest {

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    private lateinit var requestPopularMoviesUseCase: RequestPopularMoviesUseCase

    @Before
    fun setup() {
        requestPopularMoviesUseCase = RequestPopularMoviesUseCase(moviesRepository)
    }

    @Test
    fun `Invoke calls movies repository`(): Unit = runTest {
        val requestPopularMoviesUseCase = RequestPopularMoviesUseCase(moviesRepository)

        requestPopularMoviesUseCase()

        verify(moviesRepository).requestPopularMovies()
    }
}

