package com.whosaiddonut.feature.home

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.whosaiddonut.base.RxModule
import com.whosaiddonut.service.DonutService
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named

@Module
class HomeModule {

    @Provides
    fun homeViewModelFactory(activity: HomeActivity,
            donutService: DonutService, @Named(RxModule.ui) uiSchedulers: Scheduler): HomeViewModel {
        return ViewModelProviders.of(
                activity,
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                        return HomeViewModel(donutService, uiSchedulers) as T
                    }
                })
                .get(HomeViewModel::class.java)
    }
}