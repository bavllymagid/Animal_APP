package com.evapharma.animalhealth.domain.repository

interface RefreshRepository {
    suspend fun refreshToken(token:String): String?
}