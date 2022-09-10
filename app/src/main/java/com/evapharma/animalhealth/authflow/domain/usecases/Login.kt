package com.evapharma.animalhealth.authflow.domain.usecases

import com.evapharma.animalhealth.authflow.domain.model.LoginModel
import com.evapharma.animalhealth.authflow.domain.model.LoginResponseModel
import com.evapharma.animalhealth.authflow.domain.repository.CustomerRepository
import javax.inject.Inject

class Login@Inject constructor(private val customerRepository : CustomerRepository) {
    suspend fun execute(user:LoginModel) : String{
        return customerRepository.getToken(user)
    }
}