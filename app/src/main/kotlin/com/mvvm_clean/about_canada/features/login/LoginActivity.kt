package com.mvvm_clean.about_canada.features.login

import android.content.Context
import android.content.Intent
import com.mvvm_clean.about_canada.core.platform.BaseActivity

class LoginActivity : BaseActivity() {
    companion object {
        fun callingIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

    override fun fragment() = LoginFragment()
}
