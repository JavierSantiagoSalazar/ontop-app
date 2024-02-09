package com.example.ontopapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ontopapp.R
import com.example.ontopapp.databinding.FragmentDetailBinding
import com.example.ontopapp.ui.common.format
import com.example.ontopapp.ui.common.launchAndCollect
import com.example.ontopapp.ui.common.loadUrl
import com.example.ontopapp.ui.common.setVisibleOrGone
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)

        binding.movieDetailToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        setActivityComponents(visibility = false)

        viewLifecycleOwner.launchAndCollect(viewModel.state) { state ->
            if (state.movie != null) {
                with(binding) {
                    movieReleaseDate.text = state.movie.releaseDate
                    movieTitle.text = state.movie.title
                    movieDetailToolbar.title = state.movie.originalTitle
                    movieDetailSummary.text = state.movie.overview
                    movieVoteCount.text = state.movie.popularity.format()
                    movieVoteAverage.text = state.movie.voteAverage.toString()
                    movieDetailImage.loadUrl("https://image.tmdb.org/t/p/w185/${state.movie.backdropPath}")
                }
            }
        }
    }

    override fun onDestroy() {
        setActivityComponents(visibility = true)
        super.onDestroy()
    }

    private fun setActivityComponents(visibility: Boolean) {
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            .setVisibleOrGone(visibility)
    }
}
