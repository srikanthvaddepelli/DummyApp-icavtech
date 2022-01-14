package com.example.dummyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.dummyapp.database.AllUsersSharedPrefHelper
import com.example.dummyapp.fragments.HomeFragment
import com.example.dummyapp.fragments.LoginFragment
import com.example.dummyapp.fragments.SignUpFragment
import com.example.dummyapp.fragments.SplashFragment
import com.example.dummyapp.interfaces.Actions

class MainActivity : AppCompatActivity(),Actions {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSplashScreen()
    }

    private fun setSplashScreen(){
       setFragment(SplashFragment(),true,"splash")
    }

    private fun setFragment(fragment: Fragment,shouldReplace: Boolean,tag: String){
         val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        if(shouldReplace){
            fragmentTransaction.replace(R.id.main_container, fragment)
        }else{
            fragmentTransaction.add(R.id.main_container, fragment,tag)
            fragmentTransaction.addToBackStack(tag)
        }
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction.commitAllowingStateLoss()
    }

    override fun onLogoutClicked() {
        AllUsersSharedPrefHelper.getInstance(applicationContext).setUserLoggedOut()
        openSignupScreen()
    }

    override fun onSuccessfulLogin() {
        setFragment(HomeFragment(),true,"home")
    }

    override fun onSuccessfulRegistration() {
        openLoginScreen()
    }

    override fun openSignupScreen() {
        setFragment(SignUpFragment(),true,"login")
    }

    override fun openLoginScreen() {
        setFragment(LoginFragment(),false,"login")
    }


}