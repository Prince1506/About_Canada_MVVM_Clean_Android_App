package com.mvvm_clean.about_canada.features.canada_facts.domain.api

import com.mvvm_clean.about_canada.features.canada_facts.data.repo.AboutCanadaApi
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * All apis would be handled here
 */
@Singleton
class AboutCanadaApiImpl
@Inject constructor(retrofit: Retrofit) : AboutCanadaApi {

    private val aboutCanadaApi by lazy { retrofit.create(AboutCanadaApi::class.java) }
    override fun getFacts() = aboutCanadaApi.getFacts()
}
