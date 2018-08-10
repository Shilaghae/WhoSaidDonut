package com.whosaiddonut.service

import retrofit2.Retrofit

interface NetworkService {
    fun getRetrofitInstance() : Retrofit
}