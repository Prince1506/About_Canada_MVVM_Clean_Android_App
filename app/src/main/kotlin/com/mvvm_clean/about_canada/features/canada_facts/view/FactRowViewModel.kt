package com.mvvm_clean.about_canada.features.canada_facts.view

import android.os.Parcelable
import com.mvvm_clean.about_canada.core.extension.isEmptyOrNull
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FactRowViewModel(
        val title: String? = null,
        val description: String? = null,
        val imageHref: String? = null
) : Parcelable {

    val titleNotNull: String?
        get() = if (String.isEmptyOrNull(title)) "-" else title

    val descriptionNotNull: String?
        get() = if (String.isEmptyOrNull(description)) "-" else description

    val imageHrefNotNull: String?
        get() = if (String.isEmptyOrNull(imageHref)) "-" else imageHref

    fun isEmpty() =
            (title == null || title.isEmpty()) &&
                    (description == null || description.isEmpty()) &&
                    (imageHref == null || imageHref.isEmpty())
}