package com.evapharma.animalhealth.data.remote

import com.evapharma.animalhealth.domain.model.RefreshModel

interface RefreshRemoteDataSource {
    suspend fun refreshToken(token:String): String?
}