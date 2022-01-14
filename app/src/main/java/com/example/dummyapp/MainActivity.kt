package com.example.dummyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.dummyapp.fragments.SplashFragment
import com.example.dummyapp.interfaces.Actions

class MainActivity : AppCompatActivity(),Actions {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSplashScreen()
    }

    private fun setSplashScreen(){
       setFragment(SplashFragment(),true)
    }

    private fun setFragment(fragment: Fragment,shouldReplace: Boolean){
        val tag = fragment.javaClass.toString()
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
        TODO("Not yet implemented")
    }

    override fun onSuccessfulLogin() {
        TODO("Not yet implemented")
    }

    override fun onSuccessfulRegistration() {
        TODO("Not yet implemented")
    }


}