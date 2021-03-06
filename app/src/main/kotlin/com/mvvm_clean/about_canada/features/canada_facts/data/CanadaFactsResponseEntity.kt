package com.mvvm_clean.about_canada.features.canada_facts.data

import androidx.annotation.Keep
import com.mvvm_clean.about_canada.features.canada_facts.data.repo.CanadaFactsInfo

// Response for fact list API
@Keep
data class CanadaFactsResponseEntity(private val title: String, private val rows: List<RowEntity>) {
    fun toFacts() = CanadaFactsInfo(title, rows)
}
