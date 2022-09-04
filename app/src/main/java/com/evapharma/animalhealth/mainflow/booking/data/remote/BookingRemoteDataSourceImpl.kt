package com.evapharma.animalhealth.mainflow.booking.data.remote

import com.evapharma.animalhealth.mainflow.booking.domain.model.DoctorModel
import java.sql.Time
import java.util.*
import javax.inject.Inject

class BookingRemoteDataSourceImpl @Inject constructor(val api : BookingRemoteInterface,) : BookingRemoteDataSource {
    override suspend fun getDoctorList(): List<DoctorModel> {
        return api.getDoctorsList("")
    }

    override suspend fun getDoctorDays(id: String): List<Date> {
        return api.getDoctorDays("",id)
    }

    override suspend fun getDoctorsTime(id: String, day: String): List<Time> {
        return api.getDoctorsTime("",id,day)
    }

    override suspend fun sendDoctorAppointment(id: String, day: String, time: String) {
        api.sendDoctorAppointment("", id, day, time)
    }

}