package com.example.movieapps.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapps.R
import com.example.movieapps.data.dto.Genre
import com.example.movieapps.data.dto.Movie
import com.example.movieapps.databinding.ItemMovieBinding
import java.text.DecimalFormat
import java.text.SimpleDateFormat

class MovieAdapter(
    private val onClickNav: (movieId: Int) -> Unit
) : PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    inner class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500/${data.posterPath}")
                .error(R.drawable.ic_error)
                .into(binding.ivMovie)
            binding.tvTitleMovie.text = data.title
            binding.tvYear.text = data.releaseDate?.toYear()
            binding.root.setOnClickListener {
                data.id?.let { movieId ->
                    onClickNav.invoke(movieId)
                }
            }
            val decimalFormat = DecimalFormat("#.#")
            binding.tvRating.text = "${decimalFormat.format(data.voteAverage)}/10"

        }
    }

    fun String.toYear(): String {
        if (this.isNullOrEmpty()) {
            return ""
        }
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = dateFormat.parse(this)
        val yearFormat = SimpleDateFormat("yyyy")
        return yearFormat.format(date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}