package com.evapharma.animalhealth.authflow.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.evapharma.animalhealth.authflow.domain.model.CustomerModel
import com.evapharma.animalhealth.authflow.domain.model.RegResponseModel
import com.evapharma.animalhealth.authflow.domain.usecases.Register
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val register: Register): ViewModel() {
    fun registerCustomer(customer: CustomerModel): LiveData<RegResponseModel> {
            return liveData {
                emit(register.execute(customer))
            }
    }
}