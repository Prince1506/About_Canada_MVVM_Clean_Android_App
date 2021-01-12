package com.mvvm_clean.about_canada.features.movies

import com.mvvm_clean.about_canada.core.exception.Failure.FeatureFailure

class MovieFailure {
    class ListNotAvailable: FeatureFailure()
    class NonExistentMovie: FeatureFailure()
}

