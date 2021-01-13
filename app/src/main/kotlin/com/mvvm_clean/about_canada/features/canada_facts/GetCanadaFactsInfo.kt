package com.mvvm_clean.about_canada.features.canada_facts

import com.mvvm_clean.about_canada.core.interactor.UseCase
import com.mvvm_clean.about_canada.core.interactor.UseCase.None
import com.mvvm_clean.about_canada.features.canada_facts.data.repo.AboutCanadaRepository
import javax.inject.Inject

class GetCanadaFactsInfo
@Inject constructor(private val aboutCanadaRepository: AboutCanadaRepository) : UseCase<CanadaFactsInfo, None>() {

    override suspend fun run(params: None) = aboutCanadaRepository.getFacts()
}
