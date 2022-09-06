package com.evapharma.animalhealth.mainflow.booking.data.remote

import com.evapharma.animalhealth.mainflow.booking.domain.model.DoctorModel
import java.sql.Time
import java.util.*

interface BookingRemoteDataSource {
    suspend fun getDoctorList():List<DoctorModel>
    suspend fun getDoctorDays(id:String):List<Date>
    suspend fun getDoctorsTime(id:String, day:String): List<Time>
    suspend fun sendDoctorAppointment(id: String, day: String, time: String)
}