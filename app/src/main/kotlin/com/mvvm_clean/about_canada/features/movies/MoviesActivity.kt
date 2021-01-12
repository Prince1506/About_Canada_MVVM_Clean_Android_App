package com.mvvm_clean.about_canada.features.movies

import android.content.Context
import android.content.Intent
import com.mvvm_clean.about_canada.core.platform.BaseActivity

class MoviesActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, MoviesActivity::class.java)
    }

    override fun fragment() = MoviesFragment()
}
