package com.mvvm_clean.about_canada.features.movies

import com.mvvm_clean.about_canada.core.exception.Failure
import com.mvvm_clean.about_canada.core.exception.Failure.NetworkConnection
import com.mvvm_clean.about_canada.core.exception.Failure.ServerError
import com.mvvm_clean.about_canada.core.extension.empty
import com.mvvm_clean.about_canada.core.functional.Either
import com.mvvm_clean.about_canada.core.functional.Either.Left
import com.mvvm_clean.about_canada.core.functional.Either.Right
import com.mvvm_clean.about_canada.core.platform.NetworkHandler
import retrofit2.Call
import javax.inject.Inject

interface MoviesRepository {
    fun movies(): Either<Failure, Movie>

    class Network
    @Inject constructor(private val networkHandler: NetworkHandler,
                        private val service: MoviesService) : MoviesRepository {

        override fun movies(): Either<Failure, Movie> {
            return when (networkHandler.isNetworkAvailable()) {
                true -> request(service.movies(), { it.toMovie()}, MovieEntity(String.empty(), emptyList()))
                false -> Left(NetworkConnection)
            }
        }


        private fun <T, R> request(call: Call<T>,
                                   transform: (T) -> R,
                                   default: T
        ): Either<Failure, R> {

            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Right(transform((response.body() ?: default)))
                    false -> Left(ServerError)
                }
            } catch (exception: Throwable) {
                Left(ServerError)
            }
        }
    }
}
