package com.evapharma.animalhealth.authflow.data.repo

import com.evapharma.animalhealth.authflow.data.remote.RemoteDataSource
import com.evapharma.animalhealth.authflow.domain.model.CustomerModel
import com.evapharma.animalhealth.authflow.domain.model.toCustomerItem
import com.evapharma.animalhealth.authflow.domain.repo.Repo
import javax.inject.Inject

class CustomerRepoImpl @Inject constructor(private val remoteDataSource: RemoteDataSource): Repo{
    override suspend fun registerCustomer(customer: CustomerModel) {
        remoteDataSource.registerCustomer(customer.toCustomerItem())
    }


}