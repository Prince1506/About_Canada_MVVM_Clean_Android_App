package com.mvvm_clean.about_canada.features.canada_facts.data

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

// Part of canada fact list response
@Keep
@Parcelize
data class RowEntity(
        val title: String,
        val description: String,
        val imageHref: String
) : Parcelable