package com.evapharma.animalhealth.authflow.data.local

import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(val sharedPref: UserData) : LocalDataSource {

}