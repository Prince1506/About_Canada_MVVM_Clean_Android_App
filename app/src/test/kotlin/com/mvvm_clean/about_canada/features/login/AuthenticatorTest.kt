package com.mvvm_clean.about_canada.features.login

import com.mvvm_clean.about_canada.UnitTest
import com.mvvm_clean.about_canada.features.login.domain.Authenticator
import org.amshove.kluent.shouldBe
import org.junit.Test

class AuthenticatorTest : UnitTest() {

    private val authenticator = Authenticator()

    @Test fun `returns default value`() {
        authenticator.userLoggedIn() shouldBe true
    }
}
