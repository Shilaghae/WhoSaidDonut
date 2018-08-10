package com.whosaiddonut.util

import android.support.test.espresso.matcher.BoundedMatcher
import android.view.View
import com.whosaiddonut.view.DonutView
import org.hamcrest.Description
import org.hamcrest.Matcher

class AssertionUtil {

    companion object {

        fun withScore(withScore: Float): Matcher<View> {
            return object : BoundedMatcher<View, DonutView>(DonutView::class.java) {

                override fun describeTo(description: Description) {
                }

                override fun matchesSafely(donutView: DonutView): Boolean {
                    return donutView.score == withScore
                }
            }
        }
    }
}