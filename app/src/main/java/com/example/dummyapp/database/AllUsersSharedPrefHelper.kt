package com.example.dummyapp.database

import android.content.Context
import android.content.SharedPreferences
import com.example.dummyapp.model.*
import com.google.gson.Gson

class AllUsersSharedPrefHelper(private val context: Context) {

    companion object {

        var sharedPrefHelperAll: AllUsersSharedPrefHelper? = null

        fun getInstance(context: Context): AllUsersSharedPrefHelper? {

            if (sharedPrefHelperAll == null) {
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

    private fun saveJSON(key: String, userModel: UserModel) {
        val jsonString = Gson().toJson(userModel)
        val editor = sharedPreferences.edit()
        editor.putString(key, jsonString)
        editor.apply()
    }

    fun checkIfUserExists(mobile: String) = sharedPreferences.contains(mobile)

    fun getUserData(mobile: String): UserModel? {
        val json = sharedPreferences.getString(mobile, null)
        return json?.let {
            Gson().fromJson(it, UserModel::class.java)
        }
    }
}