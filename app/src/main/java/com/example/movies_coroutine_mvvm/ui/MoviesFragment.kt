package com.example.movies_coroutine_mvvm.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies_coroutine_mvvm.R
import com.example.movies_coroutine_mvvm.data.model.Movie
import com.example.movies_coroutine_mvvm.databinding.FragmentMovieBinding
import com.example.movies_coroutine_mvvm.repository.MoviesRepository
import com.example.movies_coroutine_mvvm.util.Resource

class MoviesFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MoviesAdapter
    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        binding.rvMovie.layoutManager = GridLayoutManager(activity,2)
        adapter = MoviesAdapter {
            navigateToMovieDetailFragment(it)
        }
        binding.rvMovie.adapter = adapter
    }

    private fun navigateToMovieDetailFragment(movie: Movie) {
        (requireActivity() as MainActivity).navigateMovieDetail(movie)
    }

    private fun setViewModel () {

        val viewModelFactory = ViewModelFactory(MoviesRepository())
        viewModel = ViewModelProvider(this,viewModelFactory).get(MovieViewModel::class.java)
        viewModel.getMovies()

        viewModel.movie?.observe(viewLifecycleOwner, Observer {
            if (it is Resource.Success){
                adapter.submitList(it.data?.movies?:throw Exception("Movie List Null"))
            }else{
                Log.e(TAG,"Request Error ${it.message}")
            }
            val movieList =  it.data?.movies?: emptyList()
            adapter.submitList(movieList)
        })
    }


    companion object {
        const val TAG = "MoviesFragment"

        @JvmStatic
        fun newInstance() = MoviesFragment()
    }
}