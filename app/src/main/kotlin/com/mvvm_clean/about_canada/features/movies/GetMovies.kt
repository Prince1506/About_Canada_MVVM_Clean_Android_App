package com.mvvm_clean.about_canada.features.movies

import com.mvvm_clean.about_canada.core.interactor.UseCase
import com.mvvm_clean.about_canada.core.interactor.UseCase.None
import javax.inject.Inject

class GetMovies
@Inject constructor(private val moviesRepository: MoviesRepository) : UseCase<Movie, None>() {

    override suspend fun run(params: None) = moviesRepository.movies()
}
