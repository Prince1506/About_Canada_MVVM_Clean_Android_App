package com.mvvm_clean.about_canada.features.movies

import com.mvvm_clean.about_canada.AndroidTest
import com.mvvm_clean.about_canada.core.functional.Either.Right
import com.mvvm_clean.about_canada.features.canada_facts.data.RowEntity
import com.mvvm_clean.about_canada.features.canada_facts.data.repo.CanadaFactsInfo
import com.mvvm_clean.about_canada.features.canada_facts.data.repo.GetCanadaFactsInfo
import com.mvvm_clean.about_canada.features.canada_facts.view.CanadaFactsView
import com.mvvm_clean.about_canada.features.canada_facts.view.CanadaFactsViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test

class CanadaFactsViewModelTest : AndroidTest() {

    private lateinit var moviesViewModel: CanadaFactsViewModel

    @MockK
    private lateinit var getMovies: GetCanadaFactsInfo

    @Before
    fun setUp() {
        moviesViewModel = CanadaFactsViewModel(getMovies)
    }


    @Test fun `loading movies should update live data`() {
        val candaFactList =
            CanadaFactsInfo(
                "title",
                listOf(
                    RowEntity(
                        "title",
                        "description",
                        "href"
                    )
                )
            )

        coEvery { getMovies.run(any()) } returns Right(candaFactList)

        moviesViewModel.canadaFacts.observeForever {
            it!!.factRowEntity.size shouldEqualTo 1
            it!!.factRowEntity[0].description shouldEqualTo "description"
            it!!.factRowEntity[0].title shouldEqualTo "title"
            it!!.factRowEntity[0].imageHref shouldEqualTo "href"
        }

        runBlocking { moviesViewModel.loadCanadaFacts() }
    }
}