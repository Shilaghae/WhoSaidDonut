package com.whosaiddonut.base

import com.whosaiddonut.service.ApiTestService
import com.whosaiddonut.service.NetworkService
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationTestModule {

    @Binds
    abstract fun getNetworkService(apiService: ApiTestService): NetworkService
}