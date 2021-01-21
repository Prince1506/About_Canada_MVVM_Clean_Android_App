package com.mvvm_clean.about_canada.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mvvm_clean.about_canada.R
import com.mvvm_clean.about_canada.core.constants.IKeyConstants
import com.mvvm_clean.about_canada.core.domain.extension.inTransaction
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Base Activity class with helper methods for handling fragment transactions and back button
 * events.
 *
 * @see AppCompatActivity
 */
abstract class BaseActivity : AppCompatActivity(), IKeyConstants {

    private lateinit var mScreenTitle: String

    // Override Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        setSupportActionBar(toolbar)
        addFragment(savedInstanceState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        mScreenTitle = savedInstanceState.getString(screenTitleKey, getString(R.string.dash))
        setActionTitle(mScreenTitle)

        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (::mScreenTitle.isInitialized)
            outState.putString(screenTitleKey, mScreenTitle)

        super.onSaveInstanceState(outState)
    }


    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainer) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }
    //---

    fun setActionTitle(title: String) {
        mScreenTitle = title
        toolbar_title.text = title
    }

    private fun addFragment(savedInstanceState: Bundle?) =
        savedInstanceState ?: supportFragmentManager.inTransaction {
            add(
                R.id.fragmentContainer,
                fragment()
            )
        }

    abstract fun fragment(): BaseFragment
}
