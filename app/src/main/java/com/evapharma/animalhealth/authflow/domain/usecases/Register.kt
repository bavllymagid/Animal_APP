package com.evapharma.animalhealth.authflow.domain.usecases

import com.evapharma.animalhealth.authflow.domain.model.CustomerModel
import com.evapharma.animalhealth.authflow.domain.model.RegResponseModel
import com.evapharma.animalhealth.authflow.domain.repository.CustomerRepository
import javax.inject.Inject

class Register @Inject constructor(private val customerRepository: CustomerRepository) {

    suspend fun execute(customer: CustomerModel):RegResponseModel{
        return customerRepository.registerCustomer(customer)!!
    }
}