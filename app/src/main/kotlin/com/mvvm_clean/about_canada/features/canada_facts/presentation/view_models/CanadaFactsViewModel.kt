package com.mvvm_clean.about_canada.features.canada_facts.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm_clean.about_canada.core.domain.interactor.UseCase.None
import com.mvvm_clean.about_canada.core.base.BaseViewModel
import com.mvvm_clean.about_canada.features.canada_facts.data.repo.CanadaFactsInfo
import com.mvvm_clean.about_canada.features.canada_facts.domain.use_cases.GetCanadaFactsInfo
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