package com.mvvm_clean.about_canada.features.movies

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface MoviesApi {
    companion object {
        private const val PARAM_MOVIE_ID = "movieId"
        private const val MOVIES = "/s/2iodh4vg0eortkl/facts.json"
    }

    @GET(MOVIES) fun movies(): Call<MovieEntity>
}
