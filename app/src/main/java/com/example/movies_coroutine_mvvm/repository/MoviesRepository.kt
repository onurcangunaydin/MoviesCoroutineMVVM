package com.example.movies_coroutine_mvvm.repository

import com.example.movies_coroutine_mvvm.data.api.RetrofitInstance

class MoviesRepository {
    suspend fun getMovies(page: Int) =
        RetrofitInstance.api.getMovies(page = 1)
}