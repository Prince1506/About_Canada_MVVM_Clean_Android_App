package com.mvvm_clean.about_canada.features.movies

import com.mvvm_clean.about_canada.core.extension.empty

data class Movie(val id: String, val poster: List<Row>) {

    companion object {
        val empty = Movie(String.empty(), ArrayList())
    }
}
