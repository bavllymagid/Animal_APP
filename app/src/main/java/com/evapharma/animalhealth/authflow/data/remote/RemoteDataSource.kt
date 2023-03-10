package com.evapharma.animalhealth.authflow.data.remote


import com.evapharma.animalhealth.authflow.domain.model.CustomerModel
import com.evapharma.animalhealth.authflow.domain.model.LoginModel
import com.evapharma.animalhealth.authflow.domain.model.LoginResponseModel
import com.evapharma.animalhealth.authflow.domain.model.RegResponseModel

interface RemoteDataSource {
    suspend fun getToken(user: LoginModel):LoginResponseModel?
    suspend fun registerCustomer(customer: CustomerModel):RegResponseModel?
}