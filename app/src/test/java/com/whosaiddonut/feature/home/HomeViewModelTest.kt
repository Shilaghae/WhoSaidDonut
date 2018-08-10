package com.whosaiddonut.feature.home

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.whosaiddonut.error.ErrorMatcher
import com.whosaiddonut.util.LifecycleOwnerUtil
import com.whosaiddonut.service.DonutService
import com.whosaiddonut.util.mockT
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.SingleSubject
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import java.net.UnknownHostException

class HomeViewModelTest {

    // Executes each task synchronously using Architecture Components. For more information {@link https://github.com/googlesamples/android-architecture}
    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()
    val onUpdateScoreObservable: SingleSubject<Int> = SingleSubject.create()
    val donutService: DonutService = mock(DonutService::class.java)
    val homeViewModel = HomeViewModel(donutService, Schedulers.trampoline())

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testUpdateScore() {
        `when`(donutService.getUserScore()).thenReturn(onUpdateScoreObservable)

        val observer: Observer<Int> = mockT()

        homeViewModel.retrieveData()

        homeViewModel.scoreLiveData.observe(LifecycleOwnerUtil.LIFECYCLE_OWNER_UTIL, observer)

        onUpdateScoreObservable.onSuccess(329)

        verify(observer).onChanged(329)
    }

    @Test
    fun testGeneralErrorWhileUpdatingScore() {
        `when`(donutService.getUserScore()).thenReturn(onUpdateScoreObservable)

        val observer: Observer<String> = mockT()

        homeViewModel.retrieveData()

        homeViewModel.errorLiveData.observe(LifecycleOwnerUtil.LIFECYCLE_OWNER_UTIL, observer)

        onUpdateScoreObservable.onError(Throwable())

        verify(observer).onChanged(ErrorMatcher.GENERAL_ERROR.error)
    }

    @Test
    fun testNoConnectionWhileUpdatingScore() {
        `when`(donutService.getUserScore()).thenReturn(onUpdateScoreObservable)

        val observer: Observer<String> = mockT()

        homeViewModel.retrieveData()

        homeViewModel.errorLiveData.observe(LifecycleOwnerUtil.LIFECYCLE_OWNER_UTIL, observer)

        onUpdateScoreObservable.onError(UnknownHostException())

        verify(observer).onChanged(ErrorMatcher.NO_CONNECTION.error)
    }

    @Test
    fun testUserUnauthorisedWhileUpdatingScore() {
        `when`(donutService.getUserScore()).thenReturn(onUpdateScoreObservable)

        val observer: Observer<String> = mockT()

        val httpException = mock(HttpException::class.java)

        `when`(httpException.code()).thenReturn(403)

        homeViewModel.retrieveData()

        homeViewModel.errorLiveData.observe(LifecycleOwnerUtil.LIFECYCLE_OWNER_UTIL, observer)

        onUpdateScoreObservable.onError(httpException)

        verify(observer).onChanged(ErrorMatcher.FORBIDDEN.error)
    }

    @Test
    fun testServerDownWhileUpdatingScore() {
        `when`(donutService.getUserScore()).thenReturn(onUpdateScoreObservable)

        val observer: Observer<String> = mockT()

        val httpException = mock(HttpException::class.java)

        `when`(httpException.code()).thenReturn(404)

        homeViewModel.retrieveData()

        homeViewModel.errorLiveData.observe(LifecycleOwnerUtil.LIFECYCLE_OWNER_UTIL, observer)

        onUpdateScoreObservable.onError(httpException)

        verify(observer).onChanged(ErrorMatcher.NOT_FOUND.error)
    }
}