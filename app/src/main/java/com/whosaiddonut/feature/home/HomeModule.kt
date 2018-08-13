package com.whosaiddonut.feature.home

import dagger.Binds
import dagger.Module

@Module
abstract class HomeModule {

    @Binds
    abstract fun bindHomeViewModelFactory(homeViewModelFactory: HomeViewModelFactory): ViewModelFactory

}