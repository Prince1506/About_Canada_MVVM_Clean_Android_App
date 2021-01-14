package com.mvvm_clean.about_canada.features.movies

import com.mvvm_clean.about_canada.UnitTest
import com.mvvm_clean.about_canada.core.exception.Failure.NetworkConnection
import com.mvvm_clean.about_canada.core.exception.Failure.ServerError
import com.mvvm_clean.about_canada.core.functional.Either
import com.mvvm_clean.about_canada.core.functional.Either.Right
import com.mvvm_clean.about_canada.core.platform.NetworkHandler
import com.mvvm_clean.about_canada.features.canada_facts.data.CanadaFactsEntity
import com.mvvm_clean.about_canada.features.canada_facts.data.repo.AboutCanadaApiImpl
import com.mvvm_clean.about_canada.features.canada_facts.data.repo.AboutCanadaRepository
import com.mvvm_clean.about_canada.features.canada_facts.data.repo.CanadaFactsInfo
import io.mockk.Called
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class AboutCanadaRepositoryTest : UnitTest() {

    private lateinit var networkRepository: AboutCanadaRepository.Network

    @MockK private lateinit var networkHandler: NetworkHandler
    @MockK private lateinit var service: AboutCanadaApiImpl

    @MockK private lateinit var moviesCall: Call<CanadaFactsEntity>
    @MockK private lateinit var moviesResponse: Response<CanadaFactsEntity>

    @Before fun setUp() {
        networkRepository = AboutCanadaRepository.Network(networkHandler, service)
    }

    @Test fun `should return empty POJO by default`() {
        every { networkHandler.isNetworkAvailable() } returns true
        every { moviesResponse.body() } returns null
        every { moviesResponse.isSuccessful } returns true
        every { moviesCall.execute() } returns moviesResponse
        every { service.getFacts() } returns moviesCall

        val movies = networkRepository.getFacts()

        movies shouldEqual Right(CanadaFactsInfo.empty)
        verify(exactly = 1) { service.getFacts() }
    }

    @Test fun `should get movie list from service`() {
        every { networkHandler.isNetworkAvailable() } returns true
        every { moviesResponse.body() } returns CanadaFactsEntity("", emptyList())
        every { moviesResponse.isSuccessful } returns true
        every { moviesCall.execute() } returns moviesResponse
        every { service.getFacts() } returns moviesCall

        val movies = networkRepository.getFacts()

        movies shouldEqual Right(CanadaFactsInfo.empty)
        verify(exactly = 1) { service.getFacts() }
    }

    @Test fun `movies service should return network failure when no connection`() {
        every { networkHandler.isNetworkAvailable() } returns false

        val movies = networkRepository.getFacts()

        movies shouldBeInstanceOf Either::class.java
        movies.isLeft shouldEqual true
        movies.fold({ failure -> failure shouldBeInstanceOf NetworkConnection::class.java }, {})
        verify { service wasNot Called }
    }

    @Test fun `movies service should return server error if no successful response`() {
        every { networkHandler.isNetworkAvailable() } returns true
        every { moviesResponse.isSuccessful } returns false
        every { moviesCall.execute() } returns moviesResponse
        every { service.getFacts() } returns moviesCall

        val movies = networkRepository.getFacts()

        movies shouldBeInstanceOf Either::class.java
        movies.isLeft shouldEqual true
        movies.fold({ failure -> failure shouldBeInstanceOf ServerError::class.java }, {})
    }

    @Test fun `movies request should catch exceptions`() {
        every { networkHandler.isNetworkAvailable() } returns true
        every { moviesCall.execute() } returns moviesResponse
        every { service.getFacts() } returns moviesCall

        val movies = networkRepository.getFacts()

        movies shouldBeInstanceOf Either::class.java
        movies.isLeft shouldEqual true
        movies.fold({ failure -> failure shouldBeInstanceOf ServerError::class.java }, {})
    }
}