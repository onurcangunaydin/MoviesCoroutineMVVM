package com.example.movies_coroutine_mvvm.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies_coroutine_mvvm.R
import com.example.movies_coroutine_mvvm.data.model.Result


class MoviesAdapter(private val resultList: MutableList<Result> = mutableListOf()): RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

     class MoviesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val moviePoster: ImageView = itemView.findViewById(R.id.iv_movie_poster)
        val titleTextView: TextView = itemView.findViewById(R.id.tv_title)

        fun bind(result: Result){
            titleTextView.setText(result.title)

//            val requestOptions = RequestOptions()
//                    .placeholder(R.drawable.ic_launcher_background)
//                    .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                    .load(result.posterPath)
                    .into(moviePoster)
        }
    }

     fun updateDataSet(newResultList: List<Result>){
         resultList.clear()
         resultList.addAll(newResultList)
         notifyDataSetChanged()
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movie_item,
                    parent,
                    false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(resultList.get(position))

        }


    override fun getItemCount(): Int {
       return resultList.size
    }


    private var onItemClickListener: ((Result) -> Unit)? = null

    fun setOnItemClicklistener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }
}