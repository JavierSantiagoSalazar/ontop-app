package com.example.ontopapp.usecases

import com.example.ontopapp.data.MoviesRepository
import com.example.ontopapp.testshared.sampleMovie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class GetPopularMoviesUseCaseTest {

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @Before
    fun setup() {
        getPopularMoviesUseCase = GetPopularMoviesUseCase(moviesRepository)
    }

    @Test
    fun `when GetPopularMoviesUseCase is called should returns List of Movies`(): Unit =
        runTest {
            val movies =
                flowOf(listOf(sampleMovie.copy(id = 1)))
            whenever(moviesRepository.popularMovies).thenReturn(movies)

            val result = getPopularMoviesUseCase()

            assertEquals(movies, result)
        }
}
