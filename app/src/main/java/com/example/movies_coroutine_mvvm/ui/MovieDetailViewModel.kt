package com.example.movies_coroutine_mvvm.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies_coroutine_mvvm.data.model.Movie
import com.example.movies_coroutine_mvvm.repository.MoviesRepository
import com.example.movies_coroutine_mvvm.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieDetailViewModel(private val repository: MoviesRepository) : ViewModel() {


    val movieDetail: MutableLiveData<Resource<Movie>> = MutableLiveData()

    fun getDetailMovies(id: Int?) = viewModelScope.launch {
        movieDetail.postValue(Resource.Loading())
        if (id != null){
            Log.e(TAG,"Detail Id = $id")
            val response = repository.getMovieDetail(id)
            movieDetail.postValue(handleMovieDetailResponse(response))
            Log.e(TAG, "Response = ${response.body()}")
        }else{
            Log.e(TAG,"Detail Id null")
        }
    }

    private fun handleMovieDetailResponse(response: Response<Movie>): Resource<Movie>{
        if (response.isSuccessful) {
            response.body()?.let {resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    companion object{
        const val TAG = "MovieDetailViewModel"
    }
}