package com.evapharma.animalhealth.authflow.data.remote

import com.evapharma.animalhealth.authflow.domain.model.CustomerModel
import com.evapharma.animalhealth.authflow.domain.model.RegResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CustomerAuthAPI {

    @POST("Customer/Register")
    suspend fun registerCustomer(@Body customer: CustomerModel):Response<RegResponseModel>

}