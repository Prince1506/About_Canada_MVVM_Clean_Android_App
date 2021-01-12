package com.mvvm_clean.about_canada.features.movies

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Row (
    val title: String,
    val description: String,
    val imageHref: String
):Parcelable