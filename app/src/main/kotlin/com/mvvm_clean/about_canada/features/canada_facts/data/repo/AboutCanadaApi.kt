package com.mvvm_clean.about_canada.features.canada_facts.data.repo

import com.mvvm_clean.about_canada.features.canada_facts.data.CanadaFactsEntity
import retrofit2.Call
import retrofit2.http.GET

/**
 * All apis would be trigger from here
 */
internal interface AboutCanadaApi {
    companion object {
        private const val FACTS = "/s/2iodh4vg0eortkl/facts.json"
    }

    @GET(FACTS) fun getFacts(): Call<CanadaFactsEntity>
}
