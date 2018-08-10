package com.whosaiddonut.base

import com.whosaiddonut.feature.home.HomeActivity
import com.whosaiddonut.feature.home.HomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun homeActivity(): HomeActivity
}
