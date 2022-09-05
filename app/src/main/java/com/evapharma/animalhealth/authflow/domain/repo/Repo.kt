package com.evapharma.animalhealth.authflow.domain.repo

import com.evapharma.animalhealth.authflow.domain.model.CustomerModel

interface Repo {
    suspend fun registerCustomer(customer: CustomerModel)
}