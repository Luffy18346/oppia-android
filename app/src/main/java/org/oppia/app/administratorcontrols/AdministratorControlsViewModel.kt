package org.oppia.app.administratorcontrols

import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.oppia.app.R
import org.oppia.app.fragment.FragmentScope
import org.oppia.app.profile.ProfileActivity
import org.oppia.app.viewmodel.ObservableViewModel
import javax.inject.Inject

/** [ViewModel] for [AdministratorControlsFragment]. */
@FragmentScope
class AdministratorControlsViewModel @Inject constructor(
  private val activity: AppCompatActivity,
  private val fragment: Fragment
) : ObservableViewModel() {
  private val routeToAppVersionListener = activity as RouteToAppVersionListener

  fun onAppVersionClicked() {
    routeToAppVersionListener.routeToAppVersion();
  }

  fun onLogOutClicked() {
    AlertDialog.Builder(fragment.context!!, R.style.AlertDialogTheme)
      .setMessage(R.string.log_out_dialog_message)
      .setNegativeButton(R.string.log_out_dialog_cancel_button) { dialog, _ ->
        dialog.dismiss()
      }
      .setPositiveButton(R.string.log_out_dialog_okay_button) { _, _ ->
        // TODO: Please change ProfileActivity to LoginActivity once it is added.
        val intent = Intent(fragment.activity, ProfileActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        fragment.activity!!.startActivity(intent);
        fragment.activity!!.finish()
      }.create().show()
  }
}
