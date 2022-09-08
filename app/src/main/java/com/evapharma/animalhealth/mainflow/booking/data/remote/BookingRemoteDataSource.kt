package com.evapharma.animalhealth.mainflow.booking.data.remote

import android.graphics.Bitmap
import com.evapharma.animalhealth.mainflow.booking.domain.model.*
import okhttp3.ResponseBody
import retrofit2.Response
import java.sql.Time
import java.util.*

interface BookingRemoteDataSource {
    suspend fun getDoctorList(keyword:String,pageNum:Int): DoctorModelX?
    suspend fun getDoctorDays(id:String):List<DateTimeSlot>
    suspend fun getDoctorsTime(id:String, day:String): List<DateTimeSlot>
    suspend fun sendDoctorAppointment(appointment: AppointmentModel) : Boolean
    suspend fun getBookings(id:String, pageNum:Int) : List<BookingModel>
    suspend fun getImage(url: String): Bitmap?
}