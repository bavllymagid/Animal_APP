package com.evapharma.animalhealth.mainflow.booking.data.remote

import com.evapharma.animalhealth.mainflow.booking.domain.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    override suspend fun sendDoctorAppointment(token:String, appointment: AppointmentModel): Boolean {
        var b = api.sendDoctorAppointment(token,appointment).body()?.isSuccess
        var c = b
        return c ?: false
    }

    override suspend fun getBookings(id: String): List<BookingModel> {
        return api.getUpComingBookings(id).body() ?: ArrayList()
    }

    override suspend fun getPreviousBookings(authToken: String, pageNum: Int): BookingList? {
        return api.getPreviousBookings(authToken,pageNum).body()
    }


}