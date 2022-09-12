package com.evapharma.animalhealth.mainflow.booking.data.remote

import com.evapharma.animalhealth.mainflow.booking.domain.model.*
import retrofit2.Response


interface BookingRemoteDataSource {
    suspend fun getDoctorList(keyword:String,pageNum:Int): DoctorModelX?
    suspend fun getDoctorDays(id:String): List<String>
    suspend fun getDoctorsTime(id:String, day:String): List<DateTimeSlot>
    suspend fun sendDoctorAppointment(token:String, appointment: AppointmentModel) : Boolean
    suspend fun getBookings(id:String) : List<BookingModel>
    suspend fun getPreviousBookings(authToken: String, pageNum: Int): BookingList?
}