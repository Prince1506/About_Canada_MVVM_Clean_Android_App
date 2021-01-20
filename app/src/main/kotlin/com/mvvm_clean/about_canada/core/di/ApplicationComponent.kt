package com.mvvm_clean.about_canada.core.di

import com.mvvm_clean.about_canada.AboutCanadaApplication
import com.mvvm_clean.about_canada.core.presentation.viewmodel.ViewModelModule
import com.mvvm_clean.about_canada.core.presentation.navigation.AboutCanadaActivity
import com.mvvm_clean.about_canada.features.canada_facts.presentation.ui.fragments.CanadaFactListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AboutCanadaApplication)
    fun inject(aboutCanadaActivity: AboutCanadaActivity)
    fun inject(canadaFactListFragment: CanadaFactListFragment)
}
