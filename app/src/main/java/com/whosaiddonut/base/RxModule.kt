package com.whosaiddonut.base

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class RxModule {
    companion object {
        const val io = "io"
        const val ui = "ui"
    }

    @Provides
    @Named(ui)
    fun provideUiScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @Named(io)
    fun provideIoScheduler(): Scheduler {
        return Schedulers.io()
    }
}