package com.mvvm_clean.about_canada.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvvm_clean.about_canada.features.canada_facts.presentation.models.CanadaFactsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CanadaFactsViewModel::class)
    abstract fun bindsCanadaFactsViewModel(canadaFactsViewModel: CanadaFactsViewModel): ViewModel

}