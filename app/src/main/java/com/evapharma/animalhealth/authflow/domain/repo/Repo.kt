package com.evapharma.animalhealth.authflow.domain.repo

import com.evapharma.animalhealth.authflow.domain.model.CustomerModel
import com.evapharma.animalhealth.authflow.domain.model.RegResponseModel

interface Repo {
    suspend fun registerCustomer(customer: CustomerModel):RegResponseModel?
}