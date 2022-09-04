package com.evapharma.animalhealth.applicationflow.data.remote

import com.evapharma.animalhealth.applicationflow.domain.model.DoctorModel
import java.sql.Time
import java.util.*

interface RemoteDataSource {
    suspend fun getDoctorList():List<DoctorModel>
    suspend fun getDoctorDays(id:String):List<Date>
    suspend fun getDoctorsTime(id:String, day:String): List<Time>
    suspend fun sendDoctorAppointment(id: String, day: String, time: String)
}