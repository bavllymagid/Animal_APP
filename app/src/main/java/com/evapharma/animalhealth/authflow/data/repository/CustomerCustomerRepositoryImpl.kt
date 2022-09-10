package com.evapharma.animalhealth.authflow.data.repository

import com.evapharma.animalhealth.authflow.data.local.LocalDataSource
import com.evapharma.animalhealth.authflow.data.remote.RemoteDataSource
import com.evapharma.animalhealth.authflow.domain.model.CustomerModel
import com.evapharma.animalhealth.authflow.domain.model.LoginModel
import com.evapharma.animalhealth.authflow.domain.model.LoginResponseModel
import com.evapharma.animalhealth.authflow.domain.model.RegResponseModel
import com.evapharma.animalhealth.authflow.domain.repository.CustomerRepository
import javax.inject.Inject

class CustomerCustomerRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource,
                                                         private val localDataSource: LocalDataSource): CustomerRepository{
    override suspend fun registerCustomer(customer: CustomerModel): RegResponseModel?
    {
        return remoteDataSource.registerCustomer(customer)
    }

    override suspend fun getToken(user: LoginModel): String {
        localDataSource.saveCurrentToken(remoteDataSource.getToken(user)?.token?:"")
        return localDataSource.getCurrentUserToken()
    }



}