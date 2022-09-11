package com.evapharma.animalhealth.domain.usecases

import android.content.SharedPreferences
import com.evapharma.animalhealth.domain.repository.RefreshRepository
import javax.inject.Inject

class RefreshToken @Inject constructor(val repository: RefreshRepository, val sharedPreferences: SharedPreferences) {
    suspend fun execute(token : String):String?{
        val newToken = repository.refreshToken(token)
        sharedPreferences.edit().putString("User",newToken).apply()
        return sharedPreferences.getString("User","")
    }
}