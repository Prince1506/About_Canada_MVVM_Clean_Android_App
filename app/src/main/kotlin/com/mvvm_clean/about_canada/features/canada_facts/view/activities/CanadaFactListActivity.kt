package com.mvvm_clean.about_canada.features.canada_facts.view.activities

import android.content.Context
import android.content.Intent
import com.mvvm_clean.about_canada.core.platform.BaseActivity
import com.mvvm_clean.about_canada.features.canada_facts.view.fragments.CanadaFactListFragment

class CanadaFactListActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, CanadaFactListActivity::class.java)
    }

    override fun fragment() = CanadaFactListFragment()
}
