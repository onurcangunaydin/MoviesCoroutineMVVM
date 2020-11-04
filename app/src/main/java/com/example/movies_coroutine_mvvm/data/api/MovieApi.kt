package com.example.movies_coroutine_mvvm.data.api

import com.example.movies_coroutine_mvvm.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/movie/popular")
    suspend fun getMovies(
        @Query("page")
        pageNumber: Int = 1
    ) : Response<MovieResponse>
}