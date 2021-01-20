package com.mvvm_clean.about_canada.core.presentation.navigation

import android.content.Context
import com.mvvm_clean.about_canada.features.login.domain.Authenticator
import com.mvvm_clean.about_canada.features.canada_facts.presentation.ui.activities.CanadaFactListActivity
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


