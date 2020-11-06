package com.example.movies_coroutine_mvvm.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movies_coroutine_mvvm.repository.MoviesRepository

class ViewModelFactory (val newsRepository: MoviesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(newsRepository) as T
    }
}