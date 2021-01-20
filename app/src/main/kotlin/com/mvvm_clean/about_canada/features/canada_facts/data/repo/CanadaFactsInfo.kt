package com.mvvm_clean.about_canada.features.canada_facts.data.repo

import com.mvvm_clean.about_canada.core.domain.extension.empty
import com.mvvm_clean.about_canada.features.canada_facts.data.RowEntity

/**
 *  Canada fact list response is mapped to this pojo for handling business logic
 */
data class CanadaFactsInfo(
    val title: String,
    val rows: List<RowEntity>
) {

    companion object {
        val empty = CanadaFactsInfo(String.empty(), emptyList())
    }
}