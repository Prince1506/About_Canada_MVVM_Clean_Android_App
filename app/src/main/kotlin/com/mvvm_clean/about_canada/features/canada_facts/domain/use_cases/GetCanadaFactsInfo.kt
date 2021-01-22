package com.mvvm_clean.about_canada.features.canada_facts.domain.use_cases

import com.mvvm_clean.about_canada.core.domain.interactor.UseCase
import com.mvvm_clean.about_canada.core.domain.interactor.UseCase.None
import com.mvvm_clean.about_canada.features.canada_facts.data.repo.CanadaFactsInfo
import com.mvvm_clean.about_canada.features.canada_facts.domain.repo.AboutCanadaRepository
import javax.inject.Inject


class GetCanadaFactsInfo
@Inject constructor(private val aboutCanadaRepository: AboutCanadaRepository) :
    UseCase<CanadaFactsInfo, None>() {

    override suspend fun run(params: None) = aboutCanadaRepository.getFacts()
}
