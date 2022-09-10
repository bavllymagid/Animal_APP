package com.evapharma.animalhealth.authflow.data.local

import android.annotation.SuppressLint
import android.content.SharedPreferences
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val sharedPreferences: SharedPreferences) : LocalDataSource {
    override fun getCurrentUserToken():String {
        val s = sharedPreferences.getString("User", "")
        return s?:""
    }

    override fun saveCurrentToken(token: String) {
        val s = sharedPreferences.edit().putString("User",token).apply()
    }

    override fun clearSavedToken() {
        sharedPreferences.edit().clear().apply()
    }


}