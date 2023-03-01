package com.mvvm.examplearchitecture.ui.base

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.mvvm.examplearchitecture.R
import com.mvvm.examplearchitecture.utils.ProgressBar
import timber.log.Timber

open class BaseFragment : Fragment() {

    fun navigateFragment(
        destination: NavDirections,
        fragmentExtras: FragmentNavigator.Extras? = null
    ) {
        try {
            with(findNavController()) {
                try {
                    hideSoftKeyboard(this@BaseFragment)
                    currentDestination?.getAction(destination.actionId)
                        ?.let {
                            if (fragmentExtras != null) {
                                navigate(destination, fragmentExtras)
                            } else {
                                navigate(destination)
                            }
                        } ?: kotlin.run {
                        Timber.d(
                            "Desired destination not found  \n Current destination : ${this@with.currentDestination?.id} \n Current id : ${this@with.currentDestination?.label}" +
                                    "\n Descired Destination ${destination.actionId}"
                        )
                        findNavController().navigate(R.id.navigation_graph)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    open fun hideSoftKeyboard(mFragment: Fragment?) {
        try {
            if (mFragment == null || mFragment.activity == null) {
                return
            }
            val view: View? = mFragment.requireActivity().currentFocus
            if (view != null) {
                val inputManager: InputMethodManager =
                    mFragment.requireActivity()
                        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}