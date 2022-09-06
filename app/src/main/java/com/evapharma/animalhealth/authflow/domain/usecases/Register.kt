package com.evapharma.animalhealth.authflow.domain.usecases

import com.evapharma.animalhealth.authflow.domain.model.CustomerModel
import com.evapharma.animalhealth.authflow.domain.model.RegResponseModel
import com.evapharma.animalhealth.authflow.domain.repo.Repo
import javax.inject.Inject

class Register @Inject constructor(private val customerRepo: Repo) {

    suspend fun execute(customer: CustomerModel):RegResponseModel{
        return customerRepo.registerCustomer(customer)!!
    }
}