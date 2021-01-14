package com.mvvm_clean.about_canada.features.canada_facts.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm_clean.about_canada.core.interactor.UseCase.None
import com.mvvm_clean.about_canada.core.platform.BaseViewModel
import com.mvvm_clean.about_canada.features.canada_facts.data.repo.CanadaFactsInfo
import com.mvvm_clean.about_canada.features.canada_facts.data.repo.GetCanadaFactsInfo
import javax.inject.Inject

class CanadaFactsViewModel
@Inject constructor(private val getCanadaFactsInfo: GetCanadaFactsInfo) : BaseViewModel() {

    private val mutableCandaLiveData: MutableLiveData<CanadaFactsView> = MutableLiveData()
    val canadaFacts: LiveData<CanadaFactsView> = mutableCandaLiveData

    fun loadCanadaFacts() = getCanadaFactsInfo(None()) { it.fold(::handleFailure, ::handleFactList) }

    private fun handleFactList(canadaFactsInfo: CanadaFactsInfo) {
        val rowViewModel = canadaFactsInfo.rows.flatMap {

            listOf(
                    FactRowViewModel(
                    it.title,
                    it.description,
                    it.imageHref
            ))

        }
        mutableCandaLiveData.value =  CanadaFactsView(canadaFactsInfo.title, rowViewModel)
    }
}