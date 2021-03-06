package com.whosaiddonut.base

import android.support.test.InstrumentationRegistry
import com.whosaiddonut.WhoSaidDonutTestApplication
import com.whosaiddonut.MockServer
import com.whosaiddonut.feature.home.HomeViewModel
import org.junit.After
import org.junit.Before
import javax.inject.Inject

open class BaseUiTest {

    @Inject
    lateinit var mockServer: MockServer

    @Inject
    lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        val clearScoreApp = InstrumentationRegistry.getTargetContext().applicationContext as WhoSaidDonutTestApplication
        clearScoreApp.applicationTestComponent.inject(this)
    }

    @After
    fun tearDown() {

    }
}