package com.example.movieapps.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapps.databinding.ItemGenreBinding
import com.example.movieapps.data.dto.Genre


class GenreAdapter(
    private val listGenre: List<Genre>
): RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {


    inner class GenreViewHolder(val binding: ItemGenreBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: Genre){
            binding.tvGenre.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            ItemGenreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listGenre.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(listGenre[position])
    }
}