package com.evapharma.animalhealth.authflow.data.remote

import android.util.Log
import com.evapharma.animalhealth.authflow.domain.model.CustomerModel
import com.evapharma.animalhealth.authflow.domain.model.LoginModel
import com.evapharma.animalhealth.authflow.domain.model.LoginResponseModel
import com.evapharma.animalhealth.authflow.domain.model.RegResponseModel
import javax.inject.Inject



class RemoteDataSourceImpl @Inject constructor(private val api: CustomerAuthAPI): RemoteDataSource {
    override suspend fun getToken(user:LoginModel): LoginResponseModel? {
       return api.loginCustomer(user).body()
    }


    override suspend fun registerCustomer(customer: CustomerModel): RegResponseModel?
    {
        return try{
            api.registerCustomer(customer).body()!!
        }catch (e:Exception){
            Log.d("MyApp", e.message.toString())
            null
        }
    }
}