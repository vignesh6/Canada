package com.sol.canada

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.sol.canada.idlingresource.LoadingIdlingResource
import com.sol.canada.mockserver.ErrorDispatcher
import com.sol.canada.mockserver.SuccessDispatcher
import com.sol.canada.ui.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.LooperMode

/**
 * Class responsible for UI testing
 * Used MockWebserver to fake API response
 *
 */
@RunWith(AndroidJUnit4::class)
@LooperMode(LooperMode.Mode.PAUSED)
class FactDetailsFragmentTest {
    private lateinit var mockServer: MockWebServer
    private var recyclerViewMatcher: RecyclerViewMatcher? = null
    private lateinit var loadingIdlingResource: LoadingIdlingResource

    @get:Rule
    var mainActivity: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, false, false)

    /**
     * Sets up Dagger components for testing.
     */
    @get:Rule
    val rule = DaggerTestApplicationRule()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.start(8080)
        recyclerViewMatcher = RecyclerViewMatcher(R.id.facts_recyclerview)
    }

    /**
     * Method to success flow which loads data from assets and verify the recycler item tittle matches
     */
    @Test
    fun successfullyLoadedData() {
        //Returning success data using Mockwebserver
        mockServer.dispatcher = SuccessDispatcher()
        mainActivity.launchActivity(null)
        //Waiting for progressbar to became idle
        loadingIdlingResource =
            LoadingIdlingResource(mainActivity.activity)
        IdlingRegistry.getInstance().register(loadingIdlingResource)
        //Checking against recycler view position text matches given text
        onView(withRecyclerView(R.id.facts_recyclerview).atPositionOnView(0, R.id.txt_tittle))
            .check(ViewAssertions.matches(withText("Beavers")))
        onView(withRecyclerView(R.id.facts_recyclerview).atPositionOnView(1, R.id.txt_tittle))
            .check(ViewAssertions.matches(withText("Flag")))
    }

    /**
     * Method to test failed state , Returns 404 and verify recylerview count 0
     */
    @Test
    fun failedToLoadData() {
        //Returning 404 error using Mockwebserver
        mockServer.dispatcher = ErrorDispatcher()
        mainActivity.launchActivity(null)
        loadingIdlingResource =
            LoadingIdlingResource(mainActivity.activity)
        IdlingRegistry.getInstance().register(loadingIdlingResource)
        //Performing swipe to refresh to clear data
        onView(withId(R.id.refreshFacts))
            .perform(withCustomConstraints(swipeDown(), isDisplayingAtLeast(85)))
        //Testing against recycler view size
        assertThat(getRecyclerViewCount(), Matchers.`is`(0))
    }

    private fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }

    private fun getRecyclerViewCount(): Int {
        val recyclerView =
            mainActivity.activity.findViewById(R.id.facts_recyclerview) as RecyclerView
        return recyclerView.adapter!!.itemCount
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
        IdlingRegistry.getInstance().unregister(loadingIdlingResource)
    }

    fun withCustomConstraints(action: ViewAction, constraints: Matcher<View>): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return constraints
            }

            override fun getDescription(): String {
                return action.description
            }

            override fun perform(uiController: UiController?, view: View?) {
                action.perform(uiController, view)
            }
        }
    }
}