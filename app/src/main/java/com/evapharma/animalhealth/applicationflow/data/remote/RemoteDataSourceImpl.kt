package com.evapharma.animalhealth.applicationflow.data.remote

import com.evapharma.animalhealth.applicationflow.domain.model.DoctorModel
import java.sql.Time
import java.util.*
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(val doctorsApi : DoctorsInterface,) : RemoteDataSource {
    override suspend fun getDoctorList(): List<DoctorModel> {
        return doctorsApi.getDoctorsList("")
    }

    override suspend fun getDoctorDays(id: String): List<Date> {
        return doctorsApi.getDoctorDays("",id)
    }

    override suspend fun getDoctorsTime(id: String, day: String): List<Time> {
        return doctorsApi.getDoctorsTime("",id,day)
    }

    override suspend fun sendDoctorAppointment(id: String, day: String, time: String) {
        doctorsApi.sendDoctorAppointment("", id, day, time)
    }
}