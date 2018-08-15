package com.whosaiddonut.feature.home

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.whosaiddonut.R
import com.whosaiddonut.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var homeViewModule: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModule.apply {
            loadingLiveDate.observe(this@HomeActivity, Observer { showLoading(it!!) })
            scoreLiveData.observe(this@HomeActivity, Observer { updateDonut(it) })
            errorLiveData.observe(this@HomeActivity, Observer { showError(it!!) })
        }

        buttonToSetProgress.setOnClickListener({
            homeViewModule.retrieveData()
        })
    }

    override fun getActivityViewId(): Int {
        return R.layout.activity_home
    }

    fun updateDonut(score: Int?) {
        activity_home_donutView.updateScoreWithAnimation((score ?: 0).toFloat())
    }

    fun showError(error: String) {
        Snackbar.make(activity_home_constraintLayout, error, Snackbar.LENGTH_LONG).show()
    }

    fun showLoading(isLoading: Boolean) {
        activity_home_loadingView.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
