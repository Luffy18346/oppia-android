package org.oppia.app.administratorcontrols.appversion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import org.oppia.app.R
import org.oppia.app.databinding.AppVersionFragmentBinding
import org.oppia.app.fragment.FragmentScope
import org.oppia.app.viewmodel.ViewModelProvider
import javax.inject.Inject

/** The presenter for [AppVersionFragment]. */
@FragmentScope
class AppVersionFragmentPresenter @Inject constructor(
  private val fragment: Fragment,
  private val viewModelProvider: ViewModelProvider<AppVersionViewModel>
) {
  private lateinit var appVersionToolbar: Toolbar
  private lateinit var binding: AppVersionFragmentBinding

  fun handleCreateView(inflater: LayoutInflater, container: ViewGroup?): View? {
    binding = AppVersionFragmentBinding.inflate(inflater, container, /* attachToRoot= */ false)
    binding.let {
      it.lifecycleOwner = fragment
      it.viewModel = getAppVersionViewModel()
    }
    setToolbar()
    return binding.root
  }

  private fun setToolbar() {
    appVersionToolbar = binding.root.findViewById(R.id.app_version_toolbar) as Toolbar
    appVersionToolbar.title = fragment.activity!!.getString(R.string.administrator_controls_app_version)
    appVersionToolbar.setTitleTextAppearance(fragment.activity, R.style.ToolbarTextAppearance)
    appVersionToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
    appVersionToolbar.setNavigationOnClickListener {
      fragment.activity!!.onBackPressed()
    }
  }

  private fun getAppVersionViewModel(): AppVersionViewModel {
    return viewModelProvider.getForFragment(fragment, AppVersionViewModel::class.java)
  }
}
