package com.example.cahptertujuhlatihankelompokdua.view

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.cahptertujuhlatihankelompokdua.R
import com.example.cahptertujuhlatihankelompokdua.data.UserManager

class SplashFragment  : Fragment() {
    lateinit var userManager : UserManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        userManager = UserManager(requireContext())
        Handler().postDelayed({
            userManager.userLogin.asLiveData().observe(requireActivity()){
                if (it.equals("false")){
                    view.findNavController().navigate(R.id.action_splashFragment_to_loginFragment, null,
                        NavOptions.Builder()
                            .setPopUpTo(R.id.splashFragment,
                                true).build())

                }else if (it.equals("true")){
                    view.findNavController().navigate(R.id.action_splashFragment_to_homeFragment, null,
                        NavOptions.Builder()
                            .setPopUpTo(R.id.splashFragment,
                                true).build())
                }
            }
        }, 3000)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

}