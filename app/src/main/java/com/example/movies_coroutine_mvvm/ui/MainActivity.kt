package com.example.movies_coroutine_mvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies_coroutine_mvvm.R
import com.example.movies_coroutine_mvvm.repository.MoviesRepository
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){


    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory = ViewModelFactory(MoviesRepository())

        viewModel = ViewModelProvider(this,viewModelFactory).get(MovieViewModel::class.java)
        setUI()

        viewModel.movie?.observe(this, Observer {
           val movieList =  it.data?.results?: emptyList()
            adapter.updateDataSet(movieList)
        })

        viewModel.getMovies(1)

    }

  private fun setUI (){
      rv_movie.layoutManager = LinearLayoutManager(this@MainActivity)
      adapter = MoviesAdapter()
      rv_movie.adapter = adapter

  }



}

