package com.mvvm_clean.about_canada.core.navigation

import com.mvvm_clean.about_canada.AndroidTest
import com.mvvm_clean.about_canada.features.login.Authenticator
import com.mvvm_clean.about_canada.shouldNavigateTo
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class NavigatorTest : AndroidTest() {

    private lateinit var navigator: Navigator

    @MockK private lateinit var authenticator: Authenticator

    @Before fun setup() {
        navigator = Navigator(authenticator)
    }

    @Test fun `should forward user to login screen`() {
        every { authenticator.userLoggedIn() } returns false

        verify(exactly = 1) { authenticator.userLoggedIn() }
    }

    @Test fun `should forward user to movies screen`() {
        every { authenticator.userLoggedIn() } returns true
        verify(exactly = 1) { authenticator.userLoggedIn() }
    }
}