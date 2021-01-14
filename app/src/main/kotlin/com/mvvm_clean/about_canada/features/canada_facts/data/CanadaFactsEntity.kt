package com.mvvm_clean.about_canada.features.canada_facts.data

import com.mvvm_clean.about_canada.features.canada_facts.data.repo.CanadaFactsInfo

data class CanadaFactsEntity(private val title: String, private val rows: List<RowEntity>) {
    fun toFacts() = CanadaFactsInfo(title, rows)
}
