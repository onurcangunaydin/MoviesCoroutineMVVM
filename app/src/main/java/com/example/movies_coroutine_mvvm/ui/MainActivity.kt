package com.example.movies_coroutine_mvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.movies_coroutine_mvvm.R
import com.example.movies_coroutine_mvvm.data.model.Movie
import com.example.movies_coroutine_mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.fragment_container,MoviesFragment.newInstance())
        ft.addToBackStack(null)
        ft.commit()
    }

    fun navigateMovieDetail(selectedMovie: Movie) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,MovieDetailFragment.newInstance(selectedMovie))
            .commit()
    }
}

