package org.oppia.app.administratorcontrols.appversion

import android.content.Context
import android.content.Intent
import android.os.Bundle
import org.oppia.app.activity.InjectableAppCompatActivity
import javax.inject.Inject

/** Activity for App Version. */
class AppVersionActivity : InjectableAppCompatActivity() {
  @Inject
  lateinit var appVersionActivityPresenter: AppVersionActivityPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activityComponent.inject(this)
    appVersionActivityPresenter.handleOnCreate()
  }

  companion object {
    fun createAppVersionActivityIntent(context: Context): Intent {
      return Intent(context, AppVersionActivity::class.java)
    }
  }
}