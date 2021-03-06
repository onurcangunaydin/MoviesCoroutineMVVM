package com.example.movies_coroutine_mvvm.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.movies_coroutine_mvvm.data.model.Movie
import com.example.movies_coroutine_mvvm.databinding.ItemMoviesBinding
import com.example.movies_coroutine_mvvm.databinding.ItemMoviesBindingImpl


class MoviesAdapter(private val onMovieItemClickListener: (Movie) -> Unit): ListAdapter<Movie, MoviesAdapter.MoviesViewHolder>(MoviesDiffCallback()) {

     class MoviesViewHolder( val binding: ItemMoviesBinding): RecyclerView.ViewHolder(binding.root) {

         fun bind(movie: Movie){
            binding.tvTitle.text = movie.title
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
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
        holder.binding.root.setOnClickListener {
            val selectedMovie = getItem(position)
            onMovieItemClickListener.invoke(selectedMovie)
        }
    }
}

class MoviesDiffCallback(): DiffUtil.ItemCallback<Movie>(){

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id

    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}
