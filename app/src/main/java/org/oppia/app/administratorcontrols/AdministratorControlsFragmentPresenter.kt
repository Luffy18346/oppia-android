package org.oppia.app.administratorcontrols

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.oppia.app.databinding.AdministratorControlsFragmentBinding
import org.oppia.app.fragment.FragmentScope
import org.oppia.app.viewmodel.ViewModelProvider
import javax.inject.Inject

@FragmentScope
class AdministratorControlsFragmentPresenter @Inject constructor(
  private val fragment: Fragment,
  private val viewModelProvider: ViewModelProvider<AdministratorControlsViewModel>
) {
  fun handleCreateView(inflater: LayoutInflater, container: ViewGroup?): View? {
    val binding = AdministratorControlsFragmentBinding.inflate(inflater, container, false)
    binding.let {
      it.lifecycleOwner = fragment
      it.viewModel = getAdministratorControlsViewModel()
    }
    return binding.root
  }

  private fun getAdministratorControlsViewModel(): AdministratorControlsViewModel {
    return viewModelProvider.getForFragment(fragment, AdministratorControlsViewModel::class.java)
  }
}
