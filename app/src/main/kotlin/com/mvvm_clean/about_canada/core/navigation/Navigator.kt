package com.mvvm_clean.about_canada.core.navigation

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import com.mvvm_clean.about_canada.features.login.Authenticator
import com.mvvm_clean.about_canada.features.canada_facts.view.activities.CanadaFactListActivity
import com.mvvm_clean.about_canada.core.extension.empty
import javax.inject.Inject
import javax.inject.Singleton


/**
 * This is central location to control screen flows(according to business) for complete app.
 */
@Singleton
class Navigator
@Inject constructor(private val authenticator: Authenticator) {


    fun showScreens(context: Context) {
        when (authenticator.userLoggedIn()) {
            true -> showCanadaFacts(context)
        }
    }

    private fun showCanadaFacts(context: Context) =
            context.startActivity(
                    CanadaFactListActivity.callingIntent(context)
            )

}


