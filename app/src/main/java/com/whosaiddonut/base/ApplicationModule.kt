package com.whosaiddonut.base

import com.whosaiddonut.service.ApiService
import com.whosaiddonut.service.NetworkService
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

    @Binds
    abstract fun getNetworkService(apiService: ApiService): NetworkService
}