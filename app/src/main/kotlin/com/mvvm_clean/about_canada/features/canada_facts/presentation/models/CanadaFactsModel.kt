package com.mvvm_clean.about_canada.features.canada_facts.presentation.models

import com.mvvm_clean.about_canada.core.base.KParcelable
import kotlinx.android.parcel.Parcelize

// Canada fact list model to be shown on UI
@Parcelize
data class CanadaFactsModel(val title: String, val factRowEntity: List<FactRowModel>) :
    KParcelable