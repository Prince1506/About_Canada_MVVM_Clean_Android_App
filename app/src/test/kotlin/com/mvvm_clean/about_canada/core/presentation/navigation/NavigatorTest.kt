package com.mvvm_clean.about_canada.core.presentation.navigation

import com.mvvm_clean.about_canada.AndroidTest
import com.mvvm_clean.about_canada.features.canada_facts.presentation.ui.activities.CanadaFactListActivity
import com.mvvm_clean.about_canada.features.login.domain.Authenticator
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

    @Test fun `should forward user to canada Fact screen`() {
        every { authenticator.userLoggedIn() } returns true

        navigator.showScreens(context())
        verify(exactly = 1) { authenticator.userLoggedIn() }
        AboutCanadaActivity::class shouldNavigateTo CanadaFactListActivity::class
    }
}