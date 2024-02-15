package com.example.movieapps.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapps.R
import com.example.movieapps.data.dto.ResultsItem
import com.example.movieapps.data.dto.ReviewResponse
import com.example.movieapps.databinding.ItemReviewBinding

class ReviewMovieAdapter(
    private val listReview: List<ResultsItem>
) : RecyclerView.Adapter<ReviewMovieAdapter.ReviewMovieViewHolder>() {
    inner class ReviewMovieViewHolder(val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ResultsItem) {
            if (data.authorDetails?.avatarPath == null) {
                binding.ivReview.setImageResource(R.drawable.ic_user_profil)
            } else {
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w500/" + data.authorDetails.avatarPath)
                    .error(R.drawable.ic_error)
                    .into(binding.ivReview)
            }
            binding.tvTitleReview.text = data.author
            binding.tvReview.text = data.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewMovieViewHolder {
        return ReviewMovieViewHolder(
            ItemReviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listReview.size

    override fun onBindViewHolder(holder: ReviewMovieViewHolder, position: Int) {
        holder.bind(listReview[position])
    }
}