package com.mvvm_clean.about_canada.features.canada_facts.domain.repo

import com.mvvm_clean.about_canada.core.domain.exception.Failure
import com.mvvm_clean.about_canada.core.domain.exception.Failure.NetworkConnection
import com.mvvm_clean.about_canada.core.domain.exception.Failure.ServerError
import com.mvvm_clean.about_canada.core.domain.extension.empty
import com.mvvm_clean.about_canada.core.domain.functional.Either
import com.mvvm_clean.about_canada.core.domain.functional.Either.Left
import com.mvvm_clean.about_canada.core.domain.functional.Either.Right
import com.mvvm_clean.about_canada.core.data.NetworkHandler
import com.mvvm_clean.about_canada.features.canada_facts.data.CanadaFactsResponseEntity
import com.mvvm_clean.about_canada.features.canada_facts.data.repo.CanadaFactsInfo
import com.mvvm_clean.about_canada.features.canada_facts.domain.api.AboutCanadaApiImpl
import retrofit2.Call
import javax.inject.Inject

/**
 * Api flow is controlled here
 */
interface AboutCanadaRepository {
    fun getFacts(): Either<Failure, CanadaFactsInfo>

    class Network
    @Inject constructor(private val networkHandler: NetworkHandler,
                        private val apiImpl: AboutCanadaApiImpl
    ) : AboutCanadaRepository {

        override fun getFacts(): Either<Failure, CanadaFactsInfo> {
            return when (networkHandler.isNetworkAvailable()) {
                true -> request(apiImpl.getFacts(), { it.toFacts()}, CanadaFactsResponseEntity(String.empty(), emptyList()))
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
