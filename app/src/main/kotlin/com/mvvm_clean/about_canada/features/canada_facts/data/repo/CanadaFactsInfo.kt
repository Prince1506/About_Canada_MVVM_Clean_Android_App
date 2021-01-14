package com.mvvm_clean.about_canada.features.canada_facts.data.repo

import com.mvvm_clean.about_canada.features.canada_facts.data.RowEntity

data class CanadaFactsInfo(
    val title: String,
    val rows: List<RowEntity>
) {

    companion object {
        val empty = CanadaFactsInfo("", emptyList())
    }
}