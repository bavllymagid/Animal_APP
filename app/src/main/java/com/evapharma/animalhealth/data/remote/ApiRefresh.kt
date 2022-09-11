package com.evapharma.animalhealth.data.remote

import com.evapharma.animalhealth.domain.model.RefreshModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRefresh {
    @GET("RefreshToken/rToken")
    suspend fun refreshToken(@Query("sToken") token:String) : Response<RefreshModel>
}