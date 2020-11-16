package com.example.movies_coroutine_mvvm.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies_coroutine_mvvm.data.model.MovieResponse
import com.example.movies_coroutine_mvvm.repository.MoviesRepository
import com.example.movies_coroutine_mvvm.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel(private val repository: MoviesRepository) : ViewModel() {

    init {
        getMovies()
    }

    val movie: MutableLiveData<Resource<MovieResponse>>? = MutableLiveData()

    fun getMovies() = viewModelScope.launch {
        movie?.postValue(Resource.Loading())
        val response = repository.getMovies()
        movie?.postValue(handleMovieResponse(response))

    }

    private fun handleMovieResponse(response : Response<MovieResponse>) : Resource<MovieResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}