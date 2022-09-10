package com.evapharma.animalhealth.mainflow.booking.data.remote

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.evapharma.animalhealth.mainflow.booking.domain.model.*
import javax.inject.Inject


class BookingRemoteDataSourceImpl @Inject constructor(private val api : BookingApi) : BookingRemoteDataSource {
    override suspend fun getDoctorList(keyword:String,pageNum:Int): DoctorModelX? {
        return api.getDoctorsList(keyword,pageNum).body()
    }

    override suspend fun getDoctorDays(id: String): List<String> {
        return api.getDoctorDays(id).body() ?: ArrayList()
    }

    override suspend fun getDoctorsTime(id: String, day: String): List<DateTimeSlot> {
        return api.getDoctorsTime(id,day).body() ?: ArrayList()
    }

    override suspend fun sendDoctorAppointment(appointment: AppointmentModel): Boolean {
        return api.sendDoctorAppointment(appointment).body() ?: false
    }

    override suspend fun getBookings(id: String, pageNum: Int): List<BookingModel> {
        return api.getMyBookings(id,pageNum).body() ?: ArrayList()
    }



}