package com.example.movieapps.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapps.databinding.ItemImageBinding

class ActorMovieAdapter(

):RecyclerView.Adapter<ActorMovieAdapter.ActorMovieViewHolder>() {
    inner class ActorMovieViewHolder(val binding: ItemImageBinding):
        RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorMovieViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ActorMovieViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}