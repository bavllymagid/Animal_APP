package com.evapharma.animalhealth.authflow.domain.repository

import com.evapharma.animalhealth.authflow.domain.model.CustomerModel
import com.evapharma.animalhealth.authflow.domain.model.LoginModel
import com.evapharma.animalhealth.authflow.domain.model.LoginResponseModel
import com.evapharma.animalhealth.authflow.domain.model.RegResponseModel

interface CustomerRepository {
    suspend fun registerCustomer(customer: CustomerModel):RegResponseModel?
    suspend fun getTokenRemote(user: LoginModel): String
    suspend fun getLocalToken():String
    fun clearToken()
}