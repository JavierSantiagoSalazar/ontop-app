package com.example.ontopapp.ui.movies

import app.cash.turbine.test
import com.example.ontopapp.testrules.CoroutinesTestRule
import com.example.ontopapp.testshared.sampleMovie
import com.example.ontopapp.ui.movies.MoviesViewModel.UiState
import com.example.ontopapp.usecases.GetPopularMoviesUseCase
import com.example.ontopapp.usecases.RequestPopularMoviesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @Mock
    lateinit var requestPopularMoviesUseCase: RequestPopularMoviesUseCase

    private lateinit var vm: MoviesViewModel

    private val popularMovies = listOf(sampleMovie.copy(id = 1))

    @Before
    fun setup() {
        whenever(getPopularMoviesUseCase()).thenReturn(flowOf(popularMovies))
        vm = MoviesViewModel(
            getPopularMoviesUseCase,
            requestPopularMoviesUseCase,
        )
    }

    @Test
    fun `State is updated with current cached content immediately`() = runTest {
        vm.state.test {
            assertEquals(UiState(popularMovies = popularMovies), awaitItem())
            cancel()
        }
    }

    @Test
    fun `Progress is shown when screen starts and hidden when it finishes requesting movies`() =
        runTest {
            vm.getPopularMovies()

            vm.state.test {
                assertEquals(UiState(popularMovies = popularMovies), awaitItem())
                assertEquals(
                    UiState(loading = true, popularMovies = popularMovies),
                    awaitItem(),
                )
                assertEquals(
                    UiState(
                        popularMovies = popularMovies,
                        loading = false,
                    ),
                    awaitItem(),
                )
                cancel()
            }
        }

    @Test
    fun `popularMovies are requested when UI screen starts`() = runTest {
        vm.getPopularMovies()
        runCurrent()

        verify(requestPopularMoviesUseCase).invoke()
    }
}
