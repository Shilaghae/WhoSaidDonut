package com.whosaiddonut.feature.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.whosaiddonut.base.RxModule
import com.whosaiddonut.error.handleException
import com.whosaiddonut.service.DonutService
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class HomeViewModel @Inject constructor(val donutService: DonutService, @Named(RxModule.ui) val uiScheduler: Scheduler) :
        ViewModel() {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    var scoreLiveData: MutableLiveData<Int> = MutableLiveData()

    var loadingLiveDate: MutableLiveData<Boolean> = MutableLiveData()

    var errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun retrieveData() {
        compositeDisposable.add(donutService.getUserScore()
                .observeOn(uiScheduler)
                .doOnSubscribe { loadingLiveDate.postValue(true) }
                .doOnError { loadingLiveDate.postValue(false) }
                .doOnSuccess { loadingLiveDate.postValue(false)  }
                .subscribe({ score ->
                    scoreLiveData.postValue(score)
                }, { error ->
                    error.fillInStackTrace()
                    Timber.e("Error: ${error.message}")
                    errorLiveData.postValue(handleException(error).error)
                }))

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}