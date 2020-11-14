package com.example.movies_coroutine_mvvm.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.movies_coroutine_mvvm.R
import com.example.movies_coroutine_mvvm.data.model.Result


class MoviesAdapter(): ListAdapter<Result, MoviesAdapter.MoviesViewHolder>(MoviesDiffCallback()) {

     class MoviesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val moviePoster: ImageView = itemView.findViewById(R.id.iv_movie_poster)
        private val titleTextView: TextView = itemView.findViewById(R.id.tv_title)

        fun bind(result: Result){
            titleTextView.setText(result.title)
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w342${result.posterPath}")
                .transform(CenterCrop())
                    .into(moviePoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
       return MoviesViewHolder(
        LayoutInflater.from(parent.context).inflate(
                R.layout.item_movies,
                    parent,
                    false
        )
       )
    }
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
        }
}
class MoviesDiffCallback(): DiffUtil.ItemCallback<Result>(){

    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id

    }
    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}