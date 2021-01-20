package com.mvvm_clean.about_canada.features.canada_facts.presentation.view_models

import androidx.lifecycle.MutableLiveData
import com.mvvm_clean.about_canada.AndroidTest
import com.mvvm_clean.about_canada.core.domain.functional.Either.Right
import com.mvvm_clean.about_canada.features.canada_facts.data.RowEntity
import com.mvvm_clean.about_canada.features.canada_facts.data.repo.CanadaFactsInfo
import com.mvvm_clean.about_canada.features.canada_facts.domain.use_cases.GetCanadaFactsInfo
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test

class CanadaFactsViewModelTest : AndroidTest() {

    private lateinit var canadaFactsViewModel: CanadaFactsViewModel
    private lateinit var canadaFactsInfo: CanadaFactsInfo
    private val TITLE_LBL = "title"
    private val DESCRIPTION_LBL = "description"
    private val HREF_LBL = "href"
    private val mutableCanadaLiveData: MutableLiveData<CanadaFactsView> = MutableLiveData()
    private lateinit var factRowViewModels :List<FactRowViewModel>

    @MockK
    private lateinit var getCanadaFacts: GetCanadaFactsInfo

    @Before
    fun setUp() {
        canadaFactsViewModel = CanadaFactsViewModel(getCanadaFacts)

        canadaFactsInfo = CanadaFactsInfo(
            TITLE_LBL,
            listOf(
                RowEntity(
                    TITLE_LBL,
                    DESCRIPTION_LBL,
                    HREF_LBL
                )
            )
        )

        factRowViewModels = canadaFactsInfo.rows.flatMap {

            listOf(
                FactRowViewModel(
                    it.title,
                    it.description,
                    it.imageHref
                )
            )
        }
        mutableCanadaLiveData.value = CanadaFactsView(
            canadaFactsInfo.title,
            factRowViewModels
        )
    }


    /**
     * Whenever there is a change inside API response live data should get updated.
     */
    @Test fun `loading facts should update live data`() {

        // Assert
        coEvery { getCanadaFacts.run(any()) } returns Right(canadaFactsInfo)

        // Act
        runBlocking { canadaFactsViewModel.loadCanadaFacts() }

        // Verify
        canadaFactsViewModel.canadaFacts.observeForever {
            it.let {
                it.factRowEntity.size shouldEqualTo 1
                it.factRowEntity[0].description shouldEqualTo DESCRIPTION_LBL
                it.factRowEntity[0].title shouldEqualTo TITLE_LBL
                it.factRowEntity[0].imageHref shouldEqualTo HREF_LBL
            }
        }

    }

    /**
     * Check that the response we got is passed to live data
     */
    @Test fun `correct response is passed to handleFactList method`() {

        // Assert


        // Act
       canadaFactsViewModel.handleFactList(canadaFactsInfo )

        // Verify
        mutableCanadaLiveData.value  shouldEqual CanadaFactsView(
            canadaFactsInfo.title,
            factRowViewModels
        )

    }
}