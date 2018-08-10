package com.whosaiddonut.base

import com.whosaiddonut.WhoSaidDonutApp
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ActivityBindingModule::class,
    AndroidSupportInjectionModule::class,
    RxModule::class,
    ApplicationModule::class])
interface ApplicationComponent : AndroidInjector<WhoSaidDonutApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<WhoSaidDonutApp>()
}
