package com.whosaiddonut.service

import com.whosaiddonut.BuildConfig
import com.whosaiddonut.base.RxModule
import com.google.gson.Gson
import io.reactivex.Scheduler
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named

class ApiService @Inject constructor(@Named(RxModule.io) ioSchedulers: Scheduler) : NetworkService {

    var retrofit: Retrofit

    init {
        val rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(ioSchedulers)
        retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(rxAdapter)
                .build()
    }

    override fun getRetrofitInstance(): Retrofit {
        return retrofit
    }
}