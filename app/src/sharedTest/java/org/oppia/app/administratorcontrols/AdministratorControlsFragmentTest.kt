package org.oppia.app.administratorcontrols


import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.oppia.app.R
import org.oppia.app.administratorcontrols.appversion.AppVersionActivity
import org.oppia.app.profile.ProfileActivity

class AdministratorControlsFragmentTest {

  private lateinit var activityScenario: ActivityScenario<AdministratorControlsActivity>

  @get:Rule
  var activityTestRule: ActivityTestRule<AdministratorControlsActivity> = ActivityTestRule(
    AdministratorControlsActivity::class.java, /* initialTouchMode= */ true, /* launchActivity= */ false
  )

  @Before
  fun setUp() {
    activityScenario = launchAdministratorControlsActivityIntent(0)
    Intents.init()
  }

  @Test
  fun testAdministratorControlsFragment_loadFragment_displayGeneralAndAccountSettings() {
    launchAdministratorControlsActivityIntent(0).use {
      onView(withId(R.id.general_text_view)).check(matches(isDisplayed()))
      onView(withText("Edit account")).check(matches(isDisplayed()))
      onView(withId(R.id.administrator_controls_scroll_view)).perform(ViewActions.swipeUp())
      onView(withId(R.id.account_actions_text_view)).check(matches(isDisplayed()))
      onView(withText("Log Out")).check(matches(isDisplayed()))
    }
  }

  @Test
  fun testAdministratorControlsFragment_loadFragment_displayProfileSettings() {
    launchAdministratorControlsActivityIntent(0).use {
      onView(withId(R.id.profile_management_text_view)).check(matches(isDisplayed()))
      onView(withText("Edit profiles")).check(matches(isDisplayed()))
    }
  }

  @Test
  fun testAdministratorControlsFragment_loadFragment_displayAppSettings() {
    launchAdministratorControlsActivityIntent(0).use {
      onView(withId(R.id.administrator_controls_scroll_view)).perform(ViewActions.swipeUp())
      onView(withId(R.id.app_information_text_view)).check(matches(isDisplayed()))
      onView(withText("App Version")).check(matches(isDisplayed()))
    }
  }

  @Test
  fun testAdministratorControlsFragment_loadFragment_displayDownloadPermissionsAndSettings() {
    launchAdministratorControlsActivityIntent(0).use {
      onView(withText("Download Permissions")).check(matches(isDisplayed()))
      onView(withId(R.id.topic_update_on_wifi_constraint_layout)).check(matches(isDisplayed()))
      onView(withId(R.id.administrator_controls_scroll_view)).perform(ViewActions.swipeUp())
      onView(withId(R.id.auto_update_topic_constraint_layout)).check(matches(isDisplayed()))
    }
  }

  @Test
  fun testAdministratorControlsFragment_loadFragment_topicUpdateOnWifiSwitchIsNotChecked_autoUpdateTopicSwitchIsChecked() {
    launchAdministratorControlsActivityIntent(0).use {
      onView(withId(R.id.topic_update_on_wifi_switch)).check(matches(not(isChecked())))
      onView(withId(R.id.administrator_controls_scroll_view)).perform(ViewActions.swipeUp())
      onView(withId(R.id.auto_update_topic_switch)).check(matches(isChecked()))
    }
  }

  @Test
  fun testAdministratorControlsFragment_loadFragment_clickLogoutButton_displaysLogOutDialog() {
    launchAdministratorControlsActivityIntent(0).use {
      onView(withId(R.id.log_out_text_view)).perform(click())
      onView(withText(R.string.log_out_dialog_message)).inRoot(isDialog()).check(matches(isDisplayed()))
      onView(withText(R.string.log_out_dialog_okay_button)).inRoot(isDialog()).check(matches(isDisplayed()))
      onView(withText(R.string.log_out_dialog_cancel_button)).inRoot(isDialog()).check(matches(isDisplayed()))
    }
  }

  // TODO: Please change ProfileActivity to LoginActivity once it is added.
  @Test
  fun testAdministratorControlsFragment_loadFragment_clickOkButtonInLogoutDialog_checkOpensProfileActivity() {
    launchAdministratorControlsActivityIntent(0).use {
      onView(withId(R.id.log_out_text_view)).perform(click())
      onView(withText(R.string.log_out_dialog_message)).inRoot(isDialog()).check(matches(isDisplayed()))
      onView(withText(R.string.log_out_dialog_okay_button)).perform(click())
      intended(hasComponent(ProfileActivity::class.java.name))
    }
  }

  @Test
  fun testAdministratorControlsFragment_loadFragment_clickAppVersion_checkOpensAppVersionActivity() {
    launchAdministratorControlsActivityIntent(0).use {
      onView(withId(R.id.app_version_text_view)).perform(click())
      intended(hasComponent(AppVersionActivity::class.java.name))
    }
  }

  private fun launchAdministratorControlsActivityIntent(profileId: Int?): ActivityScenario<AdministratorControlsActivity> {
    val intent = AdministratorControlsActivity.createAdministratorControlsActivityIntent(
      ApplicationProvider.getApplicationContext(),
      profileId
    )
    return ActivityScenario.launch(intent)
  }

  @After
  fun tearDown() {
    Intents.release()
  }
}