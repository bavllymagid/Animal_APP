package com.evapharma.animalhealth.data.repository

import com.evapharma.animalhealth.data.remote.RefreshRemoteDataSource
import com.evapharma.animalhealth.domain.repository.RefreshRepository
import javax.inject.Inject

class RefreshRepositoryImpl @Inject constructor(val remoteDataSource: RefreshRemoteDataSource) : RefreshRepository {
    override suspend fun refreshToken(token: String): String? {
        return remoteDataSource.refreshToken(token)
    }
}