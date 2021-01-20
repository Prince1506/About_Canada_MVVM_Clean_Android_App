package com.mvvm_clean.about_canada.features.canada_facts.presentation.view_models

import com.mvvm_clean.about_canada.core.base.KParcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CanadaFactsView(val title: String, val factRowEntity: List<FactRowViewModel>) :
    KParcelable