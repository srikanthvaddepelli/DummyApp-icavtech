package com.example.dummyapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.dummyapp.R
import com.example.dummyapp.database.AllUsersSharedPrefHelper
import com.example.dummyapp.interfaces.Actions


class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val edtPhone = view.findViewById<EditText>(R.id.edt_phone)
        view.findViewById<View>(R.id.btn_login).setOnClickListener {
            val mobile = edtPhone.text.toString()
            if(AllUsersSharedPrefHelper.getInstance(requireContext().applicationContext).checkIfUserExists(mobile)){
                Toast.makeText(requireContext(),"Login success",Toast.LENGTH_SHORT).show()
                AllUsersSharedPrefHelper.getInstance(requireContext().applicationContext).setUserLoggedIn(mobile)
                if(activity is Actions){
                    (activity as Actions).onSuccessfulLogin()
                }

            }else{
                Toast.makeText(requireContext(),"no user found",Toast.LENGTH_SHORT).show()
            }
        }
    }

}