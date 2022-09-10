package com.evapharma.animalhealth.authflow.data.remote

import android.util.Log
import com.evapharma.animalhealth.authflow.data.local.LoginModel
import com.evapharma.animalhealth.authflow.domain.model.CustomerModel
import com.evapharma.animalhealth.authflow.domain.model.RegResponseModel
import javax.inject.Inject



class RemoteDataSourceImpl @Inject constructor(private val customerAuthAPI: CustomerAuthAPI): RemoteDataSource {
    override suspend fun getToken(): List<LoginModel> {
        TODO("Not yet implemented")
    }

    override suspend fun registerCustomer(customer: CustomerModel): RegResponseModel?
    {
        return try{
            customerAuthAPI.registerCustomer(customer).body()!!
        }catch (e:Exception){
            Log.d("MyApp", e.message.toString())
            null
        }
    }
}