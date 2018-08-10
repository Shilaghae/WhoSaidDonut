package com.whosaiddonut.feature.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.whosaiddonut.error.handleException
import com.whosaiddonut.service.DonutService
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeViewModel @Inject constructor(val donutService: DonutService, val uiScheduler: Scheduler) : ViewModel() {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    var scoreLiveData: MutableLiveData<Int> = MutableLiveData()

    var errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun retrieveData() {
        compositeDisposable.add(donutService.getUserScore()
                .observeOn(uiScheduler)
                .subscribe(
                        { score -> scoreLiveData.postValue(score) },
                        { error ->
                            Timber.e("Error: ${error.message}")
                            error.fillInStackTrace()
                            errorLiveData.postValue(handleException(error).error)
                        }))

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}