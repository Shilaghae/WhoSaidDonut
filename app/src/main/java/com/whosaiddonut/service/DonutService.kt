package com.whosaiddonut.service

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class DonutService @Inject constructor(apiService: NetworkService) {

    val donutApi: DonutApi

    init {
        donutApi = apiService.getRetrofitInstance().create(DonutApi::class.java)
    }

    open fun getUserScore(): Single<Int> {
        return donutApi.getScore()
                .filter { it.creditReportInfo != null }
                .flatMapSingle { Single.just(it.creditReportInfo!!.score) }
                .subscribeOn(Schedulers.io())
    }

    interface DonutApi {

        @GET("prod/mockcredit/values")
        fun getScore(): Single<UserCreditInfo>
    }
}