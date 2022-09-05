package com.evapharma.animalhealth.authflow.data.remote

import com.evapharma.animalhealth.authflow.data.remote.models.CustomerItem

interface RemoteDataSource {
    suspend fun registerCustomer(customer: CustomerItem)
}