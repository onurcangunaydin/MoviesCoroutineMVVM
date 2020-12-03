package com.example.movies_coroutine_mvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movies_coroutine_mvvm.R
import com.example.movies_coroutine_mvvm.data.model.Movie
import com.example.movies_coroutine_mvvm.databinding.FragmentMovieDetailBinding
import com.example.movies_coroutine_mvvm.repository.MoviesRepository
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment(): Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private lateinit var viewModel: MovieDetailViewModel

    private var movieDetailId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieDetailId = arguments?.getInt(MOVIE_BUNDLE_KEY,-1)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = MovieDetailViewModelFactory(MoviesRepository())
        viewModel = ViewModelProvider(this,viewModelFactory).get(MovieDetailViewModel::class.java)
        viewModel.getDetailMovies(movieDetailId)


        viewModel.movieDetail.observe(viewLifecycleOwner, Observer {
            it.data?.let { response -> showContent(response) }
        })
    }

    private fun showContent(movie: Movie) {
        val backdrop_path = "${getString(R.string.base_image_path)}${movie.backdropPath}"
                Glide.with(this)
                        .load(backdrop_path)
                        .into(iv_movie_backdrop)

        val poster_path = "${getString(R.string.base_image_path)}${movie.posterPath}"
        Glide.with(this)
                .load(poster_path)
                .into(iv_poster)

        tv_release_date.text = movie.releaseDate
        label_vote.text = getString(R.string.label_votes,movie.voteCount.toString())
        tv_vote.text = movie.voteAverage.toString()
        tv_language.text = movie.originalLanguage
        tv_overview.text = movie.overview
    }

    companion object {
         const val MOVIE_BUNDLE_KEY = "unique_movie_key"
        @JvmStatic
        fun newInstance(movie: Movie): MovieDetailFragment {
            val args = Bundle()
            args.putInt(MOVIE_BUNDLE_KEY,movie.id)
            val detailFragment = MovieDetailFragment()
            detailFragment.arguments = args
            return detailFragment
        }
    }
}