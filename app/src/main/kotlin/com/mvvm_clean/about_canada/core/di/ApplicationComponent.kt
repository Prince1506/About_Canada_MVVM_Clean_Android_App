package com.mvvm_clean.about_canada.core.di

import com.mvvm_clean.about_canada.AboutCanadaApplication
import com.mvvm_clean.about_canada.core.di.viewmodel.ViewModelModule
import com.mvvm_clean.about_canada.core.navigation.AboutCanadaActivity
import com.mvvm_clean.about_canada.features.movies.MoviesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AboutCanadaApplication)
    fun inject(aboutCanadaActivity: AboutCanadaActivity)
    fun inject(moviesFragment: MoviesFragment)
}
