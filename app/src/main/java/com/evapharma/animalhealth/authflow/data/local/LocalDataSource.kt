package com.evapharma.animalhealth.authflow.data.local

interface LocalDataSource {
    fun getCurrentUserToken() : String
    fun saveCurrentToken(token:String)
    fun clearSavedToken()
}