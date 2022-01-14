package com.example.dummyapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import com.example.dummyapp.R

 class SignUpFragment : Fragment() {

     private var edtName: EditText? = null
     private var edtEmail: EditText? = null
     private var edtPhone: EditText? = null
     private var edtAddress: EditText? = null
     private var rbMale: RadioButton? = null
     private var rbFemale: RadioButton? = null
     private var btnSignup: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)
        initView(view)
     }

     private fun initView(view: View){
         edtName = view.findViewById(R.id.edt_name)
         edtEmail = view.findViewById(R.id.edt_email)
         edtPhone = view.findViewById(R.id.edt_phone)
         edtAddress = view.findViewById(R.id.edt_address)
         rbMale = view.findViewById(R.id.rb_male)
         rbFemale = view.findViewById(R.id.rb_female)
         btnSignup = view.findViewById(R.id.btn_signup)
         btnSignup?.setOnClickListener {

             if(isFormValid()){
                 saveToDb()
             }

         }
     }

     private fun isFormValid(): Boolean{

         return true
     }

     private fun saveToDb(){

     }

}