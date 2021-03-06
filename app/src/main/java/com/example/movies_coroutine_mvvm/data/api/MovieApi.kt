package com.example.movies_coroutine_mvvm.data.api

import com.example.movies_coroutine_mvvm.data.model.MovieResponse
import com.example.movies_coroutine_mvvm.data.model.Movie
import com.example.movies_coroutine_mvvm.util.Constans.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("/3/movie/popular")
    suspend fun getMovies(
        @Query("page")
        page: Int,
        @Query("api_key")
        apiKey: String = API_KEY
    ) : Response<MovieResponse>

    @GET("/3/movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Int?,
        @Query("api_key")
        apiKey: String = API_KEY
    ) : Response<Movie>
}