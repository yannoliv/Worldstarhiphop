package com.example.worldstarhiphop


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.worldstarhiphop.R.id.whitebarArtist
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ArtistTracksTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    // Even wachten, we testen niet op hoe snel alles open gaat,
    // maar OF alles opengaat en dat je error-loos op de tracks kan klikken
    @Test
    fun artistTracksTest() {
        Thread.sleep(3000)


        val constraintLayout = onView(
            allOf(
                withId(whitebarArtist),
                childAtPosition(
                    allOf(
                        withId(R.id.artiest_linear_layout),
                        childAtPosition(
                            withId(R.id.listArtiesten),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        constraintLayout.perform(click())

        Thread.sleep(1000)


        val constraintLayout2 = onView(
            allOf(
                withId(R.id.liedjeBalk),
                childAtPosition(
                    allOf(
                        withId(R.id.recyclerLiedjeItem),
                        childAtPosition(
                            withId(R.id.artiest_linear_layout),
                            2
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        constraintLayout2.perform(click())

        Thread.sleep(1000)

        val constraintLayout3 = onView(
            allOf(
                withId(R.id.liedjeBalk),
                childAtPosition(
                    allOf(
                        withId(R.id.recyclerLiedjeItem),
                        childAtPosition(
                            withId(R.id.artiest_linear_layout),
                            2
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        constraintLayout3.perform(click())

        Thread.sleep(1000)

        val appCompatImageView = onView(
            allOf(
                withId(R.id.imageViewPlayPause),
                childAtPosition(
                    allOf(
                        withId(R.id.relativeLayout),
                        childAtPosition(
                            withId(R.id.liedjeBalk),
                            3
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        Thread.sleep(1000)

        val constraintLayout4 = onView(
            allOf(
                withId(R.id.liedjeBalk),
                childAtPosition(
                    allOf(
                        withId(R.id.recyclerLiedjeItem),
                        childAtPosition(
                            withId(R.id.artiest_linear_layout),
                            2
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        constraintLayout4.perform(click())

        Thread.sleep(1000)

        val constraintLayout5 = onView(
            allOf(
                withId(R.id.liedjeBalk),
                childAtPosition(
                    allOf(
                        withId(R.id.recyclerLiedjeItem),
                        childAtPosition(
                            withId(R.id.artiest_linear_layout),
                            2
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        constraintLayout5.perform(click())

        Thread.sleep(1000)

        val appCompatImageView2 = onView(
            allOf(
                withId(R.id.imageViewPlayPause),
                childAtPosition(
                    allOf(
                        withId(R.id.relativeLayout),
                        childAtPosition(
                            withId(R.id.liedjeBalk),
                            3
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageView2.perform(click())

        Thread.sleep(1000)

        val constraintLayout6 = onView(
            allOf(
                withId(whitebarArtist),
                childAtPosition(
                    allOf(
                        withId(R.id.artiest_linear_layout),
                        childAtPosition(
                            withId(R.id.listArtiesten),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        constraintLayout6.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
