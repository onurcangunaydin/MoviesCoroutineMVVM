package com.example.movies_coroutine_mvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies_coroutine_mvvm.R
import com.example.movies_coroutine_mvvm.databinding.ActivityMainBinding
import com.example.movies_coroutine_mvvm.repository.MoviesRepository

class MoviesFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MoviesAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       binding = DataBindingUtil.inflate(inflater, R.layout.activity_main,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = ViewModelFactory(MoviesRepository())
        viewModel = ViewModelProvider(this,viewModelFactory).get(MovieViewModel::class.java)
        viewModel.getMovies()

        viewModel.movie?.observe(viewLifecycleOwner, Observer {
            val movieList =  it.data?.results?: emptyList()
            adapter.submitList(movieList)
        })

        binding.rvMovie.layoutManager = GridLayoutManager(activity,2)
        adapter = MoviesAdapter()
        binding.rvMovie.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = MoviesFragment()
    }
}