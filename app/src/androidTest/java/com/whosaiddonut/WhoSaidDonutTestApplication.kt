package com.whosaiddonut

import com.whosaiddonut.base.ApplicationTestComponent
import com.whosaiddonut.base.DaggerApplicationTestComponent
import dagger.android.AndroidInjector

class WhoSaidDonutTestApplication : WhoSaidDonutApp() {

    lateinit var applicationTestComponent: ApplicationTestComponent

    override fun applicationInjector(): AndroidInjector<out dagger.android.DaggerApplication> {
        applicationTestComponent = DaggerApplicationTestComponent.builder()
                .create(this) as ApplicationTestComponent
        return applicationTestComponent
    }
}