package com.example.movies_coroutine_mvvm.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies_coroutine_mvvm.data.model.Result
import com.example.movies_coroutine_mvvm.repository.MoviesRepository
import com.example.movies_coroutine_mvvm.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieDetailViewModel(private val repository: MoviesRepository) : ViewModel() {


    val movieDetail: MutableLiveData<Resource<Result>> = MutableLiveData()

    fun getDetailMovies(id: Int?) = viewModelScope.launch {
        movieDetail.postValue(Resource.Loading())
        val response = repository.getMoviesDetail(id)
        movieDetail.postValue(handleMovieDetailResponse(response))
    }

    private fun handleMovieDetailResponse(response: Response<Result>): Resource<Result>{
        if (response.isSuccessful) {
            response.body()?.let {resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}