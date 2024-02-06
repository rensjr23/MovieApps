package com.example.movieapps.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.movieapps.R
import com.example.movieapps.data.dto.CastItem
import com.example.movieapps.databinding.ItemImageBinding

class ActorMovieAdapter(
    private val listActor: List<CastItem>
) : RecyclerView.Adapter<ActorMovieAdapter.ActorMovieViewHolder>() {
    inner class ActorMovieViewHolder(val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CastItem) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500/"+data.profilePath)
                .error(R.drawable.ic_error)
                .into(binding.ivImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorMovieViewHolder {
        return ActorMovieViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listActor.size

    override fun onBindViewHolder(holder: ActorMovieViewHolder, position: Int) {
        holder.bind(listActor[position])
    }
}