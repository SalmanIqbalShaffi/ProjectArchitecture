package com.mvvm.examplearchitecture.ui.fragments.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.agentmate.utils.isNetworkConnected
import com.mvvm.examplearchitecture.R
import com.mvvm.examplearchitecture.tempmodels.LoginRequestModel
import com.mvvm.examplearchitecture.ui.base.BaseFragment
import com.mvvm.examplearchitecture.ui.fragments.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class LoginFragment : BaseFragment() {
    val viewModel: LoginViewModel by viewModels()
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        observerListener()
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    private fun observerListener() {
        viewModel.loginResponse.observe(requireActivity()) {
            //hide progress
//            progressBarShow(this@LoginActivity)
            if (it.responsecode == "200") {
                navigateFragment(LoginFragmentDirections.actionLoginFragmentToHomeFragment())

            } else if (it.responsecode == "203") {
                // do your operation
            }
        }
        viewModel.error.observe(requireActivity()) {
            val error = it.error
            Log.d("login_error", error.toString())
        }
    }

    private fun loginRequest(){
        if (requireActivity().isNetworkConnected()) {
            val loginRequest = LoginRequestModel(
                "sulman",
                "m sulman"
            )
            Log.d("loginRequest", loginRequest.toString())

            Toast.makeText(requireContext(), "Lgin Request", Toast.LENGTH_SHORT).show()
            viewModel.callLoginRequest(loginRequest)
        } else {

        }
    }
}