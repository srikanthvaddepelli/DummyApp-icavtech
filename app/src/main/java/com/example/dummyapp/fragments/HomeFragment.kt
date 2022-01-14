package com.example.dummyapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dummyapp.R
import com.example.dummyapp.database.AllUsersSharedPrefHelper
import com.example.dummyapp.interfaces.Actions
import java.lang.StringBuilder

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tv = view.findViewById<TextView>(R.id.tv)
        val stringBuilder = StringBuilder()
        val list = AllUsersSharedPrefHelper.getInstance(requireContext().applicationContext).getAllUsers()
        for(i in list.indices){
            val item = list[i]
            stringBuilder.append("(${i+1}) "+ item.phone+" "+item.name+" "+item.gender+" "+item.latLong)
            stringBuilder.append("\n\n")
        }
        tv.text = stringBuilder.toString()

        view.findViewById<View>(R.id.btn_logout).setOnClickListener {
            if(activity is Actions){
                (activity as Actions).onLogoutClicked()
            }
        }
    }

}