package com.whosaiddonut.feature.home

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.whosaiddonut.service.DonutService
import io.reactivex.android.schedulers.AndroidSchedulers

class HomeViewModelFactory constructor(val activity: HomeActivity, val donutService: DonutService) : ViewModelFactory {

    override fun getViewModel(): HomeViewModel {
        return ViewModelProviders.of(
                activity,
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                        return HomeViewModel(donutService, AndroidSchedulers.mainThread()) as T
                    }
                })
                .get(HomeViewModel::class.java)

    }

}