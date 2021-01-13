package com.mvvm_clean.about_canada.features.movies

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mvvm_clean.about_canada.core.platform.BaseFragment
import com.mvvm_clean.about_canada.R
import com.mvvm_clean.about_canada.features.movies.MovieFailure.ListNotAvailable
import com.mvvm_clean.about_canada.core.exception.Failure
import com.mvvm_clean.about_canada.core.exception.Failure.NetworkConnection
import com.mvvm_clean.about_canada.core.exception.Failure.ServerError
import com.mvvm_clean.about_canada.core.extension.failure
import com.mvvm_clean.about_canada.core.extension.invisible
import com.mvvm_clean.about_canada.core.extension.observe
import com.mvvm_clean.about_canada.core.extension.viewModel
import com.mvvm_clean.about_canada.core.extension.visible
import com.mvvm_clean.about_canada.core.navigation.Navigator
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class MoviesFragment : BaseFragment() {

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var moviesAdapter: MoviesAdapter

    private lateinit var moviesViewModel: MoviesViewModel

    override fun layoutId() = R.layout.fragment_movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        moviesViewModel = viewModel(viewModelFactory) {
            observe(movies, ::renderMoviesList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadMoviesList()
    }


    private fun initializeView() {
        movieList.layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL, false)

        movieList.adapter = moviesAdapter
    }

    private fun loadMoviesList() {
        emptyView.invisible()
        movieList.visible()
        showProgress()
        moviesViewModel.loadMovies()
    }

    private fun renderMoviesList(movies: MovieView?) {
        moviesAdapter.collection = movies?.poster!!
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is ServerError -> renderFailure(R.string.failure_server_error)
            is ListNotAvailable -> renderFailure(R.string.failure_movies_list_unavailable)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        movieList.invisible()
        emptyView.visible()
        hideProgress()
        notifyWithAction(message, R.string.action_refresh, ::loadMoviesList)
    }
}
