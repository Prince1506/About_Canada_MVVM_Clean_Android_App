package com.mvvm_clean.about_canada.features.movies

import android.os.Parcel
import android.os.Parcelable
import com.mvvm_clean.about_canada.core.platform.KParcelable
import com.mvvm_clean.about_canada.core.platform.parcelableCreator
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieView(val id: String, val poster: List<Row>) :Parcelable