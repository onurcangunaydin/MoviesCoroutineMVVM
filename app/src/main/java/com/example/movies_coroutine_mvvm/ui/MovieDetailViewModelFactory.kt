package com.example.movies_coroutine_mvvm.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movies_coroutine_mvvm.repository.MoviesRepository

class MovieDetailViewModelFactory (private val newsRepository: MoviesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(newsRepository) as T
    }
}