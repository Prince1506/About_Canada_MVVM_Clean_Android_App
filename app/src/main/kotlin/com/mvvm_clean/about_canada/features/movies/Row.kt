package com.mvvm_clean.about_canada.features.movies

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Row(
        val title: String,
        val description: String,
        val imageHref: String
) : Parcelable {

    val titleNotNull: String
        get() = if (title == null || title.isEmpty()) "-" else title

    val descriptionNotNull: String
        get() = if (description == null || description.isEmpty()) "-" else description

    val imageHrefNotNull: String
        get() = if (imageHref == null || imageHref.isEmpty()) "-" else imageHref

    fun isEmpty() =
            (title == null || title.isEmpty()) &&
                    (description == null || description.isEmpty()) &&
                    (imageHref == null || imageHref.isEmpty())
}