package com.mvvm_clean.about_canada.features.movies

import android.content.Context
import com.mvvm_clean.about_canada.features.movies.PlayMovie.Params
import com.mvvm_clean.about_canada.core.exception.Failure
import com.mvvm_clean.about_canada.core.functional.Either
import com.mvvm_clean.about_canada.core.functional.Either.Right
import com.mvvm_clean.about_canada.core.interactor.UseCase
import com.mvvm_clean.about_canada.core.interactor.UseCase.None
import com.mvvm_clean.about_canada.core.navigation.Navigator
import javax.inject.Inject

class PlayMovie
@Inject constructor(private val context: Context,
                    private val navigator: Navigator) : UseCase<None, Params>() {

    override suspend fun run(params: Params): Either<Failure, None> {
        navigator.openVideo(context, params.url)
        return Right(None())
    }

    data class Params(val url: String)
}
