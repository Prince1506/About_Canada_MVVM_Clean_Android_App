package com.mvvm_clean.about_canada.features.canada_facts.view

import com.mvvm_clean.about_canada.core.platform.KParcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CanadaFactsView(val title: String, val factRowEntity: List<FactRowViewModel>) :KParcelable