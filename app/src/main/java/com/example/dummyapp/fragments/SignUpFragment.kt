package com.example.dummyapp.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context.LOCATION_SERVICE
import android.content.SharedPreferences
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import com.example.dummyapp.R
import com.example.dummyapp.database.AllUsersSharedPrefHelper
import com.example.dummyapp.interfaces.Actions
import com.example.dummyapp.model.UserModel
import com.google.android.gms.location.LocationServices

class SignUpFragment : Fragment() {

    private var edtName: EditText? = null
    private var edtEmail: EditText? = null
    private var edtPhone: EditText? = null
    private var edtAddress: EditText? = null
    private var rbMale: RadioButton? = null
    private var rbFemale: RadioButton? = null
    private var btnSignup: View? = null
    private var btnLogin: View? = null
    private var lat: Double = 0.0
    private var long: Double = 0.0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        askLocationPermission()
    }

    private fun initView(view: View) {
        edtName = view.findViewById(R.id.edt_name)
        edtEmail = view.findViewById(R.id.edt_email)
        edtPhone = view.findViewById(R.id.edt_phone)
        edtAddress = view.findViewById(R.id.edt_address)
        rbMale = view.findViewById(R.id.rb_male)
        rbFemale = view.findViewById(R.id.rb_female)
        btnSignup = view.findViewById(R.id.btn_signup)
        btnLogin = view.findViewById(R.id.btn_login)
        btnSignup?.setOnClickListener {

            if (isFormValid()) {
                saveToDb()
            }

        }

        btnLogin?.setOnClickListener {
            if(activity is Actions){
                    (activity as Actions).openLoginScreen()
                }
            }

    }

    private fun isFormValid(): Boolean {

        return true
    }

    private fun saveToDb() {

        AllUsersSharedPrefHelper.getInstance(requireContext()).saveJSON(
            edtPhone?.text.toString(),
            UserModel(
                edtName?.text.toString(),
                edtEmail?.text.toString(),
                edtPhone?.text.toString(),
                edtAddress?.text.toString(),
                if (rbMale?.isChecked == true) "male" else "female",
                latLong = "$lat,$long"
            )
        )

        if(activity is Actions){
            (activity as Actions).onSuccessfulRegistration()
        }


    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        Toast.makeText(requireContext(), "Finding Location..", Toast.LENGTH_SHORT).show()
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    Toast.makeText(requireContext(), "Location Found", Toast.LENGTH_SHORT).show()
                    lat = location.latitude
                    long = location.longitude
                }

            }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun askLocationPermission() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    // Precise location access granted.
                    getLocation()
                }
                else -> {
                    // No location access granted.

                }
            }
        }
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
    }

}