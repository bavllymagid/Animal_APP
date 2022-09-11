package com.evapharma.animalhealth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.evapharma.animalhealth.domain.usecases.RefreshToken
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val refreshToken: RefreshToken) :ViewModel(){

    suspend fun getToken(token:String) : String?{
        return refreshToken.execute(token)
    }

}