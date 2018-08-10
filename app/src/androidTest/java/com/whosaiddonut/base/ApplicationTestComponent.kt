package com.whosaiddonut.base

import com.whosaiddonut.WhoSaidDonutTestApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ActivityBindingModule::class,
    AndroidSupportInjectionModule::class,
    RxTestModule::class,
    ApplicationTestModule::class])

interface ApplicationTestComponent : AndroidInjector<WhoSaidDonutTestApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<WhoSaidDonutTestApplication>()

    fun inject(baseUiTest: BaseUiTest)
}