package com.example.movies_coroutine_mvvm.repository

import com.example.movies_coroutine_mvvm.data.api.RetrofitInstance

class MoviesRepository {
    suspend fun getMovies() =
            RetrofitInstance.api.getMovies(page = 2)

    suspend fun getMoviesDetail(id: Int?) =
            RetrofitInstance.api.getMovieDetail(id = id)
}