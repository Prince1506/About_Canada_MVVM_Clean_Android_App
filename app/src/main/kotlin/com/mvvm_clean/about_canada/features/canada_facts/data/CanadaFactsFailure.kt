package com.mvvm_clean.about_canada.features.canada_facts.data

import com.mvvm_clean.about_canada.core.domain.exception.Failure.FeatureFailure

/**
 * Data to be shown when canada facts API fail
 */
class CanadaFactsFailure {
    class ListNotAvailable: FeatureFailure()
}

