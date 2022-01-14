package com.example.dummyapp.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dummyapp.R
import com.example.dummyapp.database.AllUsersSharedPrefHelper
import com.example.dummyapp.interfaces.Actions


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isUserLogin = AllUsersSharedPrefHelper.getInstance(requireContext()).isUserLogin()

            Handler().postDelayed({
                if(activity is Actions){
                        if(isUserLogin){
                            (activity as Actions).onSuccessfulLogin()
                        }else{
                            (activity as Actions).openSignupScreen()
                        }
                }
            }, 3000)

    }


}