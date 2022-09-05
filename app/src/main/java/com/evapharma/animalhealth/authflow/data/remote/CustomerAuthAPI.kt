package com.evapharma.animalhealth.authflow.data.remote

import com.evapharma.animalhealth.authflow.data.remote.models.CustomerItem
import com.evapharma.animalhealth.authflow.domain.model.CustomerModel
import retrofit2.http.POST

interface CustomerAuthAPI {

    @POST("/Customer/Register")
    suspend fun registerCustomer(customer: CustomerItem)

}