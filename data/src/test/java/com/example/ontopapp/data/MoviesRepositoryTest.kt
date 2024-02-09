package com.example.ontopapp.data

import arrow.core.right
import com.example.ontopapp.data.datasource.MoviesLocalDataSource
import com.example.ontopapp.data.datasource.MoviesRemoteDataSource
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
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MoviesRepositoryTest {

    @Mock
    lateinit var localDataSource: MoviesLocalDataSource

    @Mock
    lateinit var remoteDataSource: MoviesRemoteDataSource

    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        moviesRepository = MoviesRepository(localDataSource, remoteDataSource)
    }

    @Test
    fun `Popular movies are taken from local data source if available`(): Unit = runTest {
        val localMovies = flowOf(listOf(sampleMovie.copy(1)))
        whenever(localDataSource.movies).thenReturn(localMovies)

        val result = moviesRepository.popularMovies

        assertEquals(localMovies, result)
    }

    @Test
    fun `Popular movies are saved to local data source when it's empty`(): Unit = runTest {
        val remoteMovies = listOf(sampleMovie.copy(2))
        whenever(localDataSource.isEmpty()).thenReturn(true)
        whenever(remoteDataSource.findPopularMovies()).thenReturn(remoteMovies.right())

        moviesRepository.requestPopularMovies()

        verify(localDataSource).save(remoteMovies)
    }

    @Test
    fun `Finding a movie by id is done in local data source`(): Unit = runTest {
        val movie = flowOf(sampleMovie.copy(id = 5))
        whenever(localDataSource.findById(5)).thenReturn(movie)

        val result = moviesRepository.findById(5)

        assertEquals(movie, result)
    }
}
