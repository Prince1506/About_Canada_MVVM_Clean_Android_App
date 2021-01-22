package com.mvvm_clean.about_canada.features.canada_facts.presentation.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm_clean.about_canada.core.base.BaseViewModel
import com.mvvm_clean.about_canada.core.domain.exception.Failure
import com.mvvm_clean.about_canada.core.domain.interactor.UseCase.None
import com.mvvm_clean.about_canada.features.canada_facts.data.repo.CanadaFactsInfo
import com.mvvm_clean.about_canada.features.canada_facts.domain.use_cases.GetCanadaFactsInfo
import javax.inject.Inject


/**
 * View Model responsible for showing canada fact list on screen.
 * It will interact with both data as well as UI layer
 */
class CanadaFactsViewModel @Inject constructor(private val getCanadaFactsInfo: GetCanadaFactsInfo) :
    BaseViewModel() {

    private val isProgressLoading = MutableLiveData<Boolean>()

    fun getIsLoading(): LiveData<Boolean?>? {
        return isProgressLoading
    }

    private val mutableCanadaLiveData: MutableLiveData<CanadaFactsModel> by lazy {
        MutableLiveData<CanadaFactsModel>().also {
            loadCanadaFacts()
        }
    }

    val canadaFacts: LiveData<CanadaFactsModel> = mutableCanadaLiveData

    fun loadCanadaFacts() {
        isProgressLoading.value = true
        getCanadaFactsInfo(None()) { it.fold(::handleFactListFailure, ::handleFactList) }
    }


    private fun handleFactListFailure(failure: Failure) {
        super.handleFailure(failure)
        isProgressLoading.value = false
    }

    fun handleFactList(canadaFactsInfo: CanadaFactsInfo) {
        val rowViewModel = canadaFactsInfo.rows.flatMap {
            listOf(FactRowModel(it.title, it.description, it.imageHref))
        }

        mutableCanadaLiveData.value = CanadaFactsModel(canadaFactsInfo.title, rowViewModel)
        isProgressLoading.value = false
    }
}