package com.mvvm_clean.about_canada.core.di

import com.mvvm_clean.about_canada.AboutCanadaApplication
import com.mvvm_clean.about_canada.core.di.viewmodel.ViewModelModule
import com.mvvm_clean.about_canada.core.navigation.AboutCanadaActivity
import com.mvvm_clean.about_canada.features.canada_facts.view.fragments.CanadaFactListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AboutCanadaApplication)
    fun inject(aboutCanadaActivity: AboutCanadaActivity)
    fun inject(canadaFactListFragment: CanadaFactListFragment)
}
