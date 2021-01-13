package com.mvvm_clean.about_canada.features.canada_facts.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RowEntity(
        val title: String,
        val description: String,
        val imageHref: String
) : Parcelable