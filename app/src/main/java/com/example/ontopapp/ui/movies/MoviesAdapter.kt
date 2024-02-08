package com.example.ontopapp.ui.movies

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ontopapp.R
import com.example.ontopapp.databinding.ItemMovieBinding
import com.example.ontopapp.domain.models.Movie
import com.example.ontopapp.ui.common.basicDiffUtil
import com.example.ontopapp.ui.common.inflate
import com.example.ontopapp.ui.common.loadUrl

class MoviesAdapter(private val listener: (Movie) -> Unit) :
    ListAdapter<Movie, MoviesAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_movie, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
        holder.itemView.setOnClickListener { listener(movie) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemMovieBinding.bind(view)
        fun bind(movie: Movie) = with(binding) {
            tvMovieTittle.text = movie.title
            imMovieImage.loadUrl("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
        }
    }
}
