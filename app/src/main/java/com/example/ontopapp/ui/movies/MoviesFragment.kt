package com.example.ontopapp.ui.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ontopapp.R
import com.example.ontopapp.databinding.FragmentMoviesBinding
import com.example.ontopapp.domain.models.Movie
import com.example.ontopapp.ui.diff
import com.example.ontopapp.ui.launchAndCollect
import com.example.ontopapp.ui.setVisibleOrGone
import com.example.ontopapp.ui.showErrorSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private val viewModel: MoviesViewModel by viewModels()

    private val moviesAdapter = MoviesAdapter { onMovieClicked(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMoviesBinding.bind(view).apply {
            recyclerMovies.adapter = moviesAdapter
        }

        with(viewModel.state) {
            diff(this, { it.loading }) {
                it.let { binding.progress.setVisibleOrGone(it) }
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

    private fun onMovieClicked(movie: Movie) {
        //
    }
}
