package com.jerogaren.characterslistmarvelmvvm

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.jerogaren.characterslistmarvelmvvm.view.CharactersAdapter
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class InstrumentedTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.jerogaren.characterslistmarvelmvvm", appContext.packageName)
    }

    @Test
    fun testList(){
        Thread.sleep(3000)
        Espresso.onView(withId(R.id.rvCharacters)).perform(
            RecyclerViewActions.actionOnItemAtPosition<CharactersAdapter.CharacterViewHolder>(0,
                ViewActions.click()
            ))

        Espresso.onView(withId(R.id.rvCharacters))
            .perform(swipeUp())
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.rvCharactersDetails)).perform(
            RecyclerViewActions.actionOnItemAtPosition<CharactersAdapter.CharacterViewHolder>(5,
                ViewActions.click()
            ))
    }
}