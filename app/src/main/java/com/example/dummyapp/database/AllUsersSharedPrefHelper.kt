package com.example.dummyapp.database

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.dummyapp.model.*
import com.google.gson.Gson


class AllUsersSharedPrefHelper(private val context: Context) {

    companion object {

        private lateinit var sharedPrefHelperAll: AllUsersSharedPrefHelper

        fun getInstance(context: Context): AllUsersSharedPrefHelper {

            if (!::sharedPrefHelperAll.isInitialized) {
                sharedPrefHelperAll = AllUsersSharedPrefHelper(context)
            }

            return sharedPrefHelperAll
        }
    }

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(
            "ALLUSERS",
            0
        )
    }

    private val activeUserSharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(
            "ACTIVEUSER",
            0
        )
    }

      fun isUserLogin() = activeUserSharedPreferences.getString("mobile", "").isNullOrBlank().not()

      fun setUserLoggedIn(mobile: String){
        activeUserSharedPreferences.edit().apply {
            putString("mobile",mobile)
            apply()
        }
    }

    fun setUserLoggedOut(){
        activeUserSharedPreferences.edit().apply {
            remove("mobile")
            apply()
        }
    }

    fun saveJSON(key: String, userModel: UserModel) {
        val jsonString = Gson().toJson(userModel)
        sharedPreferences.edit().apply{
            putString(key, jsonString)
            apply()
        }
    }

    fun checkIfUserExists(mobile: String) = sharedPreferences.contains(mobile)

    fun getUserData(mobile: String): UserModel? {
        val json = sharedPreferences.getString(mobile, null)
        return json?.let {
            Gson().fromJson(it, UserModel::class.java)
        }
    }

    fun getAllUsers(): ArrayList<UserModel>{
        val list = ArrayList<UserModel>()
        val keys: Map<String, *> = sharedPreferences.all

        for ((_, value) in keys) {
           val userModel = Gson().fromJson(value.toString(), UserModel::class.java)
           list.add(userModel)
        }

        return list
    }
}