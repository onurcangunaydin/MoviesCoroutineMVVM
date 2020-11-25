package com.example.movies_coroutine_mvvm.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.movies_coroutine_mvvm.data.model.Result
import com.example.movies_coroutine_mvvm.databinding.ItemMoviesBinding
import com.example.movies_coroutine_mvvm.databinding.ItemMoviesBindingImpl


class MoviesAdapter(): ListAdapter<Result, MoviesAdapter.MoviesViewHolder>(MoviesDiffCallback()) {

     class MoviesViewHolder(private val binding: ItemMoviesBinding): RecyclerView.ViewHolder(binding.root) {

         fun bind(result: Result){
            binding.tvTitle.text = result.title
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w342${result.posterPath}")
                .transform(CenterCrop())
                    .into(binding.ivMoviePoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMoviesBindingImpl.inflate(layoutInflater,parent,false)
        return MoviesViewHolder(binding)
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