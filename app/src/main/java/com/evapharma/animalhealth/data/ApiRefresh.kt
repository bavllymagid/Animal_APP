package com.evapharma.animalhealth.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRefresh {
    @GET("RefreshToken/rToken")
    fun refreshToken(@Query("sToken") token:String)
}