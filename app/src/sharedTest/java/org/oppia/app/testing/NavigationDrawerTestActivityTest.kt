package org.oppia.app.testing

import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.junit.Test
import org.junit.runner.RunWith
import org.oppia.app.R
import org.oppia.app.recyclerview.RecyclerViewMatcher

/** Tests for [NavigationDrawerTestActivity]. */
@RunWith(AndroidJUnit4::class)
class NavigationDrawerTestActivityTest {
  @Test
  fun testNavigationDrawerTestActivity_clickNavigationDrawerHamburger_navigationDrawerIsOpenedSuccessfully() {
    ActivityScenario.launch(NavigationDrawerTestActivity::class.java).use {
      onView(withContentDescription(R.string.drawer_open_content_description)).check(
        matches(isDisplayed())
      ).perform(click())
      onView(withId(R.id.home_fragment_placeholder))
        .check(matches(isDisplayed()))
      onView(withId(R.id.home_activity_drawer_layout)).check(matches(DrawerMatchers.isOpen()))
    }
  }

  @Test
  fun testNavigationDrawerTestActivity_clickNavigationDrawerHamburger_closingOfNavigationDrawerIsVerifiedSucessfully() {
    ActivityScenario.launch(NavigationDrawerTestActivity::class.java).use {
      onView(withContentDescription(R.string.drawer_open_content_description)).perform(click())
      onView(withId(R.id.home_activity_drawer_layout)).perform(DrawerActions.close())
      onView(withId(R.id.home_activity_drawer_layout)).check(matches(DrawerMatchers.isClosed()))
    }
  }

  @Test
  fun testNavigationDrawerTestActivity_clickNavigationDrawerHamburger_selectHelpMenuInNavigationDrawer_showsHelpFragmentSuccessfully() {
    ActivityScenario.launch(NavigationDrawerTestActivity::class.java).use {
      onView(withContentDescription(R.string.drawer_open_content_description))
        .perform(click())
      onView(withText(R.string.menu_help)).perform(click())
      onView(
        allOf(
          instanceOf(TextView::class.java),
          withParent(withId(R.id.help_activity_toolbar))
        )
      ).check(matches(withText(R.string.menu_help)))
    }
  }

  @Test
  fun testNavigationDrawerTestActivity_clickNavigationDrawerHamburger_selectHelpMenuInNavigationDrawer_clickNavigationDrawerHamburger_navigationDrawerIsOpenedAndVerifiedSuccessfully() {
    ActivityScenario.launch(NavigationDrawerTestActivity::class.java).use {
      onView(withContentDescription(R.string.drawer_open_content_description))
        .perform(click())
      onView(withText(R.string.menu_help)).perform(click())
      onView(
        allOf(
          instanceOf(TextView::class.java),
          withParent(withId(R.id.help_activity_toolbar))
        )
      )
        .check(matches(withText(R.string.menu_help)))
      onView(withContentDescription(R.string.drawer_open_content_description)).check(
        matches(isDisplayed())
      ).perform(click())
      onView(withId(R.id.help_activity_drawer_layout))

    }
  }

  @Test
  fun testNavigationDrawerTestActivity_clickNavigationDrawerHamburger_selectHelpMenuInNavigationDrawer_openingAndClosingOfDrawerIsVerifiedSuccessfully() {
    ActivityScenario.launch(NavigationDrawerTestActivity::class.java).use {
      onView(withContentDescription(R.string.drawer_open_content_description))
        .perform(click())
      onView(withText(R.string.menu_help)).perform(click())
      onView(withContentDescription(R.string.drawer_open_content_description))
        .perform(click())
      onView(withId(R.id.help_activity_drawer_layout)).perform(DrawerActions.close())
      onView(withId(R.id.help_activity_drawer_layout)).check(matches(DrawerMatchers.isClosed()))
      onView(
        allOf(
          instanceOf(TextView::class.java),
          withParent(withId(R.id.help_activity_toolbar))
        )
      ).check(matches(withText(R.string.menu_help)))
      onView(withId(R.id.help_activity_drawer_layout)).perform(DrawerActions.open())
      onView(withId(R.id.help_activity_drawer_layout)).check(matches(DrawerMatchers.isOpen()))
    }
  }

  @Test
  fun testNavigationDrawerTestActivity_clickNavigationDrawerHamburger_selectHelpMenuInNavigationDrawer_navigationDrawerClosingIsVerifiedSuccessfully() {
    ActivityScenario.launch(NavigationDrawerTestActivity::class.java).use {
      onView(withContentDescription(R.string.drawer_open_content_description))
        .perform(click())
      onView(withText(R.string.menu_help)).perform(click())
      onView(withContentDescription(R.string.drawer_open_content_description))
        .perform(click())
      onView(
        allOf(
          instanceOf(TextView::class.java),
          withParent(withId(R.id.help_activity_toolbar))
        )
      ).check(matches(withText(R.string.menu_help)))
      onView(withId(R.id.help_activity_drawer_layout)).perform(DrawerActions.close())
      onView(withId(R.id.help_activity_drawer_layout)).check(matches(DrawerMatchers.isClosed()))
    }
  }

  @Test
  fun testNavigationDrawerTestActivity_clickNavigationDrawerHamburger_selectHelpMenuInNavigationDrawer_selectHomeMenuInNavigationDrawer_showsHomeFragmentSuccessfully() {
    ActivityScenario.launch(NavigationDrawerTestActivity::class.java).use {
      onView(withContentDescription(R.string.drawer_open_content_description))
        .perform(click())
      onView(withText(R.string.menu_help)).perform(click())
      onView(withContentDescription(R.string.drawer_open_content_description))
        .perform(click())
      onView(withText(R.string.menu_home)).perform(click())
      onView(
        allOf(
          instanceOf(TextView::class.java),
          withParent(withId(R.id.home_activity_toolbar))
        )
      ).check(matches(withText(R.string.menu_home)))
      onView(
        RecyclerViewMatcher.atPositionOnView(
          R.id.home_recycler_view,
          0,
          R.id.welcome_text_view
        )
      ).check(matches(withText("Welcome to Oppia!")))
    }
  }
}