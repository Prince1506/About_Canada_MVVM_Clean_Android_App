package com.mvvm_clean.about_canada

import android.app.Application
import com.mvvm_clean.about_canada.core.di.ApplicationComponent
import com.mvvm_clean.about_canada.core.di.ApplicationModule
import com.mvvm_clean.about_canada.core.di.DaggerApplicationComponent

class AboutCanadaApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

}
