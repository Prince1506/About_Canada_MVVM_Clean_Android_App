package com.mvvm_clean.about_canada.core.base

import androidx.lifecycle.MutableLiveData
import com.mvvm_clean.about_canada.AndroidTest
import com.mvvm_clean.about_canada.core.domain.exception.Failure
import com.mvvm_clean.about_canada.core.domain.exception.Failure.NetworkConnection
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Test

class BaseViewModelTest : AndroidTest() {

    @Test fun `should handle failure by updating live data`() {
        val viewModel = MyViewModel()

        viewModel.handleError(NetworkConnection)

        val failure = viewModel.failure
        val error = viewModel.failure.value

        failure shouldBeInstanceOf MutableLiveData::class.java
        error shouldBeInstanceOf NetworkConnection::class.java
    }

    private class MyViewModel : BaseViewModel() {
        fun handleError(failure: Failure) = handleFailure(failure)
    }
}