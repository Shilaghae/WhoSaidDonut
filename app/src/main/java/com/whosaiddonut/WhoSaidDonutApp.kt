package com.whosaiddonut

import com.whosaiddonut.base.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

open class WhoSaidDonutApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder()
                .create(this)
    }
}