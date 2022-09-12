package com.evapharma.animalhealth.authflow.domain.usecases

import com.evapharma.animalhealth.authflow.domain.repository.CustomerRepository
import javax.inject.Inject

class GetLocalToken @Inject constructor(val repository: CustomerRepository) {
    suspend fun execute():String{
        return repository.getLocalToken()
    }

    fun clearExecute(){
        repository.clearToken()
    }
}