package com.mvvm_clean.about_canada.features.movies

data class MovieEntity(private val title: String, private val rows : List<Row>) {
    fun toMovie() = Movie(title, rows)
}
