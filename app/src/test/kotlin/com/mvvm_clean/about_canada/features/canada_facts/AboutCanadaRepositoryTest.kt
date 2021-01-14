package com.mvvm_clean.about_canada.features.canada_facts

import com.mvvm_clean.about_canada.UnitTest
import com.mvvm_clean.about_canada.core.exception.Failure.NetworkConnection
import com.mvvm_clean.about_canada.core.exception.Failure.ServerError
import com.mvvm_clean.about_canada.core.functional.Either
import com.mvvm_clean.about_canada.core.functional.Either.Right
import com.mvvm_clean.about_canada.core.platform.NetworkHandler
import com.mvvm_clean.about_canada.features.canada_facts.data.CanadaFactsEntity
import com.mvvm_clean.about_canada.features.canada_facts.data.RowEntity
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

    private val TITLE_LBL = "title"
    private val DESCRIPTION_LBL = "description"
    private val HREF_LBL = "href"

    @MockK private lateinit var networkHandler: NetworkHandler
    @MockK private lateinit var service: AboutCanadaApiImpl
    private lateinit var canadaFactsEntity: CanadaFactsEntity
    @MockK private lateinit var canadaFactsCall: Call<CanadaFactsEntity>
    @MockK private lateinit var canadaFactsResponse: Response<CanadaFactsEntity>

    @Before fun setUp() {
        networkRepository = AboutCanadaRepository.Network(networkHandler, service)
        canadaFactsEntity = CanadaFactsEntity(
            TITLE_LBL,
            listOf(
                RowEntity(
                    TITLE_LBL,
                    DESCRIPTION_LBL,
                    HREF_LBL
                )
            )
        )

    }

    /**
     * If API succeeded and we get response as null. Then it should return empty object
     */
    @Test fun `should return empty POJO by default`() {
        // Assert
        every { networkHandler.isNetworkAvailable() } returns true
        every { canadaFactsResponse.body() } returns null
        every { canadaFactsResponse.isSuccessful } returns true
        every { canadaFactsCall.execute() } returns canadaFactsResponse
        every { service.getFacts() } returns canadaFactsCall

        // Act
        val canadaFacts= networkRepository.getFacts()

        //Verify
        canadaFacts shouldEqual Right(CanadaFactsInfo.empty)
        verify(exactly = 1) { service.getFacts() }
    }

    /**
     * Case when API succeeded and we get proper response from API then same should be returned by our method too.
     */
    @Test fun `should get canada facts list from service`() {
        // Assert
        every { networkHandler.isNetworkAvailable() } returns true
        every { canadaFactsResponse.body() } returns canadaFactsEntity
        every { canadaFactsResponse.isSuccessful } returns true
        every { canadaFactsCall.execute() } returns canadaFactsResponse
        every { service.getFacts() } returns canadaFactsCall

        // Act
        val canadaFacts = networkRepository.getFacts()

        // Verify
        canadaFacts shouldEqual Right(canadaFactsEntity.toFacts())
        verify(exactly = 1) { service.getFacts() }
    }

    /**
     * Check if API fails due to no network app should show no internet message
     */
    @Test fun `canada facts service should return network failure when no connection`() {
        // Assert
        every { networkHandler.isNetworkAvailable() } returns false

        // Act
        val canadaFacts = networkRepository.getFacts()

        // Verify
        canadaFacts shouldBeInstanceOf Either::class.java
        canadaFacts.isLeft shouldEqual true
        canadaFacts.fold({ failure -> failure shouldBeInstanceOf NetworkConnection::class.java }, {})
        verify { service wasNot Called }
    }

    /**
     * Check if API fails due to server error app should should show server error popup
     */
    @Test fun `canada facts service should return server error if no successful response`() {
        // Assert
        every { networkHandler.isNetworkAvailable() } returns true
        every { canadaFactsResponse.isSuccessful } returns false
        every { canadaFactsCall.execute() } returns canadaFactsResponse
        every { service.getFacts() } returns canadaFactsCall

        // Act
        val canadaFacts = networkRepository.getFacts()

        // Verify
        canadaFacts shouldBeInstanceOf Either::class.java
        canadaFacts.isLeft shouldEqual true
        canadaFacts.fold({ failure -> failure shouldBeInstanceOf ServerError::class.java }, {})
    }

    /**
     * Check if API fails due to any unhandled exception app should not get crash
     */
    @Test fun `fact request should catch exceptions`() {
        // Assert
        every { networkHandler.isNetworkAvailable() } returns true
        every { canadaFactsCall.execute() } returns canadaFactsResponse
        every { service.getFacts() } returns canadaFactsCall

        // Act
        val canadaFacts = networkRepository.getFacts()

        // Verify
        canadaFacts shouldBeInstanceOf Either::class.java
        canadaFacts.isLeft shouldEqual true
        canadaFacts.fold({ failure -> failure shouldBeInstanceOf ServerError::class.java }, {})
    }
}