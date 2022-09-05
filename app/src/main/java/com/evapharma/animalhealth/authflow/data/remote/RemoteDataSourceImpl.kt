package com.evapharma.animalhealth.authflow.data.remote

import com.evapharma.animalhealth.authflow.data.remote.models.CustomerItem
import com.evapharma.animalhealth.authflow.domain.model.CustomerModel
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val customerAuthAPI: CustomerAuthAPI): RemoteDataSource {
    override suspend fun registerCustomer(customer: CustomerItem) {
        customerAuthAPI.registerCustomer(customer)
    }
}