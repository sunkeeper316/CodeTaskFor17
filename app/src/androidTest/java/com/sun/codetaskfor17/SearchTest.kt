package com.sun.codetaskfor17

import android.util.Log
import android.view.inputmethod.EditorInfo.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
//@RunWith(AndroidJUnit4::class)
@LargeTest
class SearchTest {

    @Rule
    @JvmField
    var activityActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun search_startActivity() {
        //輸入搜尋id
        onView(withId(R.id.et_searchview)).perform(typeText("sun"),pressImeActionButton())

        Thread.sleep(5000)
        onView(withId(R.id.rv_user)).perform(swipeUp())

        Thread.sleep(5000)

        onView(withId(R.id.rv_user)).perform(swipeUp())
        Thread.sleep(5000)

    }

}