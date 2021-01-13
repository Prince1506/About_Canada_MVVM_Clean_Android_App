package com.mvvm_clean.about_canada.features.canada_facts.view

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FactRowViewModel(
        val title: String? = null,
        val description: String? = null,
        val imageHref: String? = null
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