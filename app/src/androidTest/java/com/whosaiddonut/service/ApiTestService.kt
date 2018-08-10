package com.whosaiddonut.service

import com.whosaiddonut.MockServer
import com.whosaiddonut.base.RxModule
import com.google.gson.Gson
import io.reactivex.Scheduler
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named

class ApiTestService @Inject constructor(val mockServer: MockServer, @Named(RxModule.io) val ioScheduler: Scheduler) :
        NetworkService {

    override fun getRetrofitInstance(): Retrofit {
        val rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(ioScheduler)
        return Retrofit.Builder()
                .baseUrl(mockServer.url())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(rxAdapter)
                .build()
    }

}