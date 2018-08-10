package com.whosaiddonut.base

import android.os.AsyncTask
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class RxTestModule {

    companion object {
        const val io = "io"
        const val ui = "ui"
    }

    @Provides
    @Named(ui)
    fun provideUiScheduler(): Scheduler {
        return Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    @Provides
    @Named(io)
    fun provideIoScheduler(): Scheduler {
        return Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)
    }
}