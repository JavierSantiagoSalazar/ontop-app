package com.example.ontopapp.ui.movies

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ontopapp.R
import com.example.ontopapp.databinding.FragmentMoviesBinding
import com.example.ontopapp.domain.models.Movie
import com.example.ontopapp.ui.common.diff
import com.example.ontopapp.ui.common.launchAndCollect
import com.example.ontopapp.ui.common.setVisibleOrGone
import com.example.ontopapp.ui.common.showErrorSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private lateinit var binding: FragmentMoviesBinding

    private val viewModel: MoviesViewModel by viewModels()

    private val moviesAdapter = MoviesAdapter { onMovieClicked(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMoviesBinding.bind(view).apply {
            recyclerMovies.adapter = moviesAdapter
            swipeRefresh.setOnRefreshListener {
                viewModel.getPopularMovies()
                swipeRefresh.isRefreshing = false
            }
        }

        with(viewModel.state) {
            diff(this, { it.loading }) {
                it.let { binding.progress.setVisibleOrGone(it) }
            }
            diff(this, { it.popularMovies }) { popularMovies ->
                binding.etFilter.addTextChangedListener { userFilter ->
                    val filteredMoviesList = popularMovies?.filter { movie ->
                        movie.title.lowercase().contains(userFilter.toString().lowercase())
                    }
                    moviesAdapter.submitList(filteredMoviesList)
                }
            }
            diff(this, { it.popularMovies }) { moviesAdapter.submitList(it) }
            launchAndCollect(this) {
                it.error?.let { error ->
                    view.showErrorSnackBar(error) {
                        viewModel.getPopularMovies()
                    }
                }
            }
        }
        viewModel.getPopularMovies()
    }

    override fun onResume() {
        super.onResume()
        binding.etFilter.setText("")
    }

    private fun onMovieClicked(movie: Movie) {
        val action = MoviesFragmentDirections.actionMoviesToDetail(movie.id)
        findNavController().navigate(action)
    }
}
