package org.oppia.app.administratorcontrols

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.oppia.app.BuildConfig
import org.oppia.app.R
import org.oppia.app.administratorcontrols.appversion.AppVersionActivity

class AppVersionFragmentTest {

  private lateinit var activityScenario: ActivityScenario<AppVersionActivity>

  @get:Rule
  var activityTestRule: ActivityTestRule<AppVersionActivity> = ActivityTestRule(
    AppVersionActivity::class.java, /* initialTouchMode= */ true, /* launchActivity= */ false
  )

  @Before
  fun setUp() {
    activityScenario = launchAppVersionActivityIntent()
    Intents.init()
  }

  fun testAppVersionFragment_loadFragment_displaysAppVersion() {
    launchAppVersionActivityIntent().use {
      onView(withText("App Version ${BuildConfig.VERSION_NAME}")).check(matches(isDisplayed()))
      onView(ViewMatchers.withId(R.id.app_information_text_view))
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
      onView(ViewMatchers.withText("App Version")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
  }

  private fun launchAppVersionActivityIntent(): ActivityScenario<AppVersionActivity> {
    val intent = AppVersionActivity.createAppVersionActivityIntent(
      ApplicationProvider.getApplicationContext()
    )
    return ActivityScenario.launch(intent)
  }

  @After
  fun tearDown() {
    Intents.release()
  }
}