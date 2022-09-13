package com.evapharma.animalhealth.data.remote

import com.evapharma.animalhealth.domain.model.RefreshModel
import javax.inject.Inject

class RefreshRemoteDataSourceImpl @Inject constructor(val api:ApiRefresh) : RefreshRemoteDataSource  {
    override suspend fun refreshToken(token: String): String? {
        return api.refreshToken(token).body()?.Token
    }
}