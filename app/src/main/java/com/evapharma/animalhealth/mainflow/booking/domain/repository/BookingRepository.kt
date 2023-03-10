package com.evapharma.animalhealth.mainflow.booking.domain.repository

import android.graphics.Bitmap
import com.evapharma.animalhealth.mainflow.booking.data.local.model.LocalBooking
import com.evapharma.animalhealth.mainflow.booking.domain.model.*

interface BookingRepository {
    suspend fun getDoctorList(keyword:String,pageNum:Int): DoctorModelX?
    suspend fun getDoctorDays(id:String): List<String>
    suspend fun getDoctorsTime(id:String, day:String): List<DateTimeSlot>
    suspend fun sendDoctorAppointment(token:String, appointment: AppointmentModel) : Boolean
    suspend fun getBookingsRemote(id:String) : List<BookingModel>
    suspend fun cacheBookings(bookingList: List<LocalBooking>)
    suspend fun getMyBookings(): List<LocalBooking>
    suspend fun getPrevBookings(id:String, pageNum:Int): BookingList?
    suspend fun deleteLocal()
}