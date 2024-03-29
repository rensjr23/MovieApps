package com.example.movieapps.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapps.R
import com.example.movieapps.data.dto.SearchItem
import com.example.movieapps.databinding.ItemMovieBinding
import java.text.DecimalFormat
import java.text.SimpleDateFormat

class SearchMovieAdapter(
    private val listSearch: List<SearchItem>,
    private val onClickNav: (movieId: Int) -> Unit
) : RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder>() {
    inner class SearchMovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SearchItem) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500/${data.posterPath}")
                .error(R.drawable.ic_error)
                .into(binding.ivMovie)
            binding.tvTitleMovie.text = data.title
            binding.tvYear.text = data.releaseDate?.toYear()
            binding.root.setOnClickListener {
                data.id?.let { movieId -> onClickNav.invoke(movieId) }
            }
            val decimalFormat = DecimalFormat("#.#")
            binding.tvRating.text = "${decimalFormat.format(data.voteAverage)}/10"
        }
    }

    fun String.toYear(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = dateFormat.parse(this)
        val yearFormat = SimpleDateFormat("yyyy")
        return yearFormat.format(date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        return SearchMovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listSearch.size

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        holder.bind(listSearch[position])
    }
}