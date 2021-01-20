package com.mvvm_clean.about_canada.features.canada_facts.data.repo

import com.mvvm_clean.about_canada.UnitTest
import com.mvvm_clean.about_canada.core.domain.functional.Either.Right
import com.mvvm_clean.about_canada.core.domain.interactor.UseCase
import com.mvvm_clean.about_canada.features.canada_facts.data.RowEntity
import com.mvvm_clean.about_canada.features.canada_facts.domain.repo.AboutCanadaRepository
import com.mvvm_clean.about_canada.features.canada_facts.data.repo.CanadaFactsInfo
import com.mvvm_clean.about_canada.features.canada_facts.domain.use_cases.GetCanadaFactsInfo
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCanadaFactsInfoTest : UnitTest() {

    private lateinit var getCanadaFactsInfo: GetCanadaFactsInfo
    private lateinit var canadaFactList: CanadaFactsInfo
    private val TITLE_LBL = "title"
    private val DESCRIPTION_LBL = "description"
    private val HREF_LBL = "href"
    @MockK private lateinit var aboutCanadaRepository: AboutCanadaRepository

    @Before fun setUp() {

        getCanadaFactsInfo = GetCanadaFactsInfo(aboutCanadaRepository)
        canadaFactList = CanadaFactsInfo(
            TITLE_LBL,
            listOf(
                RowEntity(
                    TITLE_LBL,
                    DESCRIPTION_LBL,
                    HREF_LBL
                )
            )
        )
        every { aboutCanadaRepository.getFacts() } returns Right(CanadaFactsInfo.empty)
    }

    /**
     * Check that API method is called once to get fact list
     */
    @Test fun `should get data from repository`() {

        // Act
        runBlocking { getCanadaFactsInfo.run(UseCase.None()) }

        // Verify
        verify(exactly = 1) { aboutCanadaRepository.getFacts() }
    }
}
