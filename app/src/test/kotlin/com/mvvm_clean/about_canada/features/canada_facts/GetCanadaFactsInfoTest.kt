package com.mvvm_clean.about_canada.features.movies

import com.mvvm_clean.about_canada.UnitTest
import com.mvvm_clean.about_canada.core.functional.Either.Right
import com.mvvm_clean.about_canada.core.interactor.UseCase
import com.mvvm_clean.about_canada.features.canada_facts.data.repo.AboutCanadaRepository
import com.mvvm_clean.about_canada.features.canada_facts.data.repo.CanadaFactsInfo
import com.mvvm_clean.about_canada.features.canada_facts.data.repo.GetCanadaFactsInfo
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCanadaFactsInfoTest : UnitTest() {

    private lateinit var getCanadaFactsInfo: GetCanadaFactsInfo

    @MockK private lateinit var aboutCanadaRepository: AboutCanadaRepository

    @Before fun setUp() {
        getCanadaFactsInfo = GetCanadaFactsInfo(aboutCanadaRepository)
        every { aboutCanadaRepository.getFacts() } returns Right(CanadaFactsInfo.empty)
    }

    @Test fun `should get data from repository`() {
        runBlocking { getCanadaFactsInfo.run(UseCase.None()) }

        verify(exactly = 1) { aboutCanadaRepository.getFacts() }
    }
}
