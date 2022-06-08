package com.example.tours_android.view


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import androidx.test.filters.LargeTest
import com.example.tours_android.R
import com.example.tours_android.service.EspressoIdlingResource
import com.example.tours_android.viewmodels.MovieAdapter
import junit.framework.TestCase
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeFragmentTest {
    private lateinit var homeFragment: HomeFragment

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    val ITEM_NUMBER = 0


    /**
     * RecyclerView comes into view
     */
    @Test
    fun recyclerView_isDisplayed() {
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun scrollToItem() {
        onView(withId(R.id.recycler_view))
            .perform(
            RecyclerViewActions.scrollToPosition<MovieAdapter.MovieViewHolder>(8))

    }

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }


    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun checkItemName(){

        onView(ViewMatchers.withId(R.id.recycler_view))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
//        val  expected:String = "Coco"
//
//
//        onView(withText(expected)).check(matches(isDisplayed()))
    }

    /**
     *Select an item from the RecyclerView
     * then check if the correct details is displayed
     */
    @Test
    fun selectItem() {
        onView(withId(R.id.recycler_view))
            .perform(
                actionOnItemAtPosition<MovieAdapter.MovieViewHolder>(ITEM_NUMBER, click())
            )

        //pass
        onView(withId(R.id.tour_title_tv)).check(matches(withText("Coco")))
        //fail
       // onView(withId(R.id.tour_title_tv)).check(matches(withText("ABC")))
    }

    /**
     * select item, nav to the item details screen
     * press back button
     */
    @Test
    fun selectItem_navigateToDetails() {
        onView(withId(R.id.recycler_view))
            .perform(
                actionOnItemAtPosition<MovieAdapter.MovieViewHolder>(ITEM_NUMBER, click())
            )

        onView(withId(R.id.tour_title_tv)).check(matches(withText("Coco")))
        pressBack()
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }
}

