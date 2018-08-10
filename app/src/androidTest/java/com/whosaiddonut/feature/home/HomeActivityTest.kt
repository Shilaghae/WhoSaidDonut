package com.whosaiddonut.feature.home

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.whosaiddonut.R.id.activity_home_donutView
import com.whosaiddonut.R.id.buttonToSetProgress
import com.whosaiddonut.base.BaseUiTest
import com.whosaiddonut.error.ErrorMatcher
import com.whosaiddonut.util.AssertionUtil.Companion.withScore
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test

class HomeActivityTest : BaseUiTest() {

    @Rule
    @JvmField
    var activityTestRule: ActivityTestRule<HomeActivity> = ActivityTestRule(HomeActivity::class.java,
            false,
            true)

    @Test
    fun testUserHasCreditScore_CreditScoreIsVisible() {
        mockServer.setUserCreditScoreInfo("user_credit_score_info.json")
        onView(withId(buttonToSetProgress)).perform(click())
        onView(withId(activity_home_donutView)).check(matches(withScore(320f)))
    }

    @Test
    fun testUserIsNotAuthorised_CannotRetrieveCreditScore() {
        mockServer.missingAuthenticationToken()
        onView(withId(buttonToSetProgress)).perform(click())
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(ErrorMatcher.FORBIDDEN.error)))
                .check(matches(isDisplayed()))
    }

    @Test
    fun testServerIsDown_UserCannotRetrieveCreditScore() {
        mockServer.setServerDown()
        onView(withId(buttonToSetProgress)).perform(click())

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(ErrorMatcher.NOT_FOUND.error)))
                .check(matches(isDisplayed()))
    }

    @Test
    fun testScoreIsNotAvailable_UserCannotSeeCreditScore() {
        mockServer.setNoCreditScore()
        onView(withId(buttonToSetProgress)).perform(click())
        onView(withId(activity_home_donutView)).check(matches(withScore(0f)))
    }

    @Test
    fun testUserRetrieveCreditScoreMultipleTimes() {
        mockServer.setUserCreditScoreInfo("user_credit_score_info.json")
        onView(withId(buttonToSetProgress)).perform(click())
        onView(withId(activity_home_donutView)).check(matches(withScore(320f)))

        mockServer.setUserCreditScoreInfo("user_credit_score_info_2.json")
        onView(withId(buttonToSetProgress)).perform(click())
        onView(withId(activity_home_donutView)).check(matches(withScore(512f)))
    }
}
