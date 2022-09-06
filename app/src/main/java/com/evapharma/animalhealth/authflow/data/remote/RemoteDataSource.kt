package com.evapharma.animalhealth.authflow.data.remote

import com.evapharma.animalhealth.authflow.domain.model.CustomerModel
import com.evapharma.animalhealth.authflow.domain.model.RegResponseModel

interface RemoteDataSource {
    suspend fun registerCustomer(customer: CustomerModel):RegResponseModel?
}