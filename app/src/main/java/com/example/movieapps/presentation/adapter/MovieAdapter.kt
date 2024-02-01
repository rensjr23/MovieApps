package com.example.movieapps.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapps.R
import com.example.movieapps.data.dto.Genre
import com.example.movieapps.data.dto.Movie
import com.example.movieapps.databinding.ItemMovieBinding
import java.text.SimpleDateFormat

class MovieAdapter(
    private val context: Context,
    private val listMovie: List<Movie>,
    private val onClickNav: (movieId: Int) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/${data.posterPath}")
                .error(R.drawable.ic_error)
                .into(binding.ivMovie)
            binding.tvTitleMovie.text = data.title
            binding.tvYear.text = data.releaseDate?.toYear()
            binding.root.setOnClickListener {
                data.id?.let { movieId -> onClickNav.invoke(movieId) }
            }

        }
    }

    fun String.toYear(): String {
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

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }
}