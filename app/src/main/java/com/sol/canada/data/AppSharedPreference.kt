package com.sol.canada.data

import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Helper class for SharedPreference
 */
class AppSharedPreference @Inject constructor(private val sharedPreference: SharedPreferences){
    fun getStringData(key: String) = sharedPreference.getString(key,"")
    fun putStringData(key: String,data:String) =  sharedPreference.edit().putString(key,data).apply()
}