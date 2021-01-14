package com.mvvm_clean.about_canada.features.canada_facts.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm_clean.about_canada.core.interactor.UseCase.None
import com.mvvm_clean.about_canada.core.platform.BaseViewModel
import com.mvvm_clean.about_canada.features.canada_facts.data.repo.CanadaFactsInfo
import com.mvvm_clean.about_canada.features.canada_facts.data.repo.GetCanadaFactsInfo
import javax.inject.Inject

class CanadaFactsViewModel @Inject constructor(private val getCanadaFactsInfo: GetCanadaFactsInfo) : BaseViewModel() {

    private val mutableCanadaLiveData: MutableLiveData<CanadaFactsView> = MutableLiveData()
    val canadaFacts: LiveData<CanadaFactsView> = mutableCanadaLiveData

    fun loadCanadaFacts() = getCanadaFactsInfo(None()) { it.fold(::handleFailure, ::handleFactList) }

    fun handleFactList(canadaFactsInfo: CanadaFactsInfo) {
        val rowViewModel = canadaFactsInfo.rows.flatMap {
            listOf( FactRowViewModel(it.title, it.description, it.imageHref ))
        }

        mutableCanadaLiveData.value =  CanadaFactsView(canadaFactsInfo.title, rowViewModel)
    }
}