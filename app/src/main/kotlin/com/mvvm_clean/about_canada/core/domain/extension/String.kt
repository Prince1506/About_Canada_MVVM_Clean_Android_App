package com.mvvm_clean.about_canada.core.domain.extension

fun String.Companion.empty() = ""

fun String.Companion.isEmptyOrNull(mString: String?): Boolean =
    mString == null || mString.isEmpty()