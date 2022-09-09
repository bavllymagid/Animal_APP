package com.evapharma.animalhealth.mainflow.booking.domain.repository

import android.graphics.Bitmap
import com.evapharma.animalhealth.mainflow.booking.data.local.model.LocalBooking
import com.evapharma.animalhealth.mainflow.booking.domain.model.*

interface BookingRepository {
    suspend fun getDoctorList(keyword:String,pageNum:Int): DoctorModelX?
    suspend fun getDoctorDays(id:String):List<DateTimeSlot>
    suspend fun getDoctorsTime(id:String, day:String): List<DateTimeSlot>
    suspend fun sendDoctorAppointment(appointment: AppointmentModel) : Boolean
    suspend fun getBookingsRemote(id:String, pageNum:Int) : List<BookingModel>
    suspend fun cacheBookings(bookingList: List<LocalBooking>)
    suspend fun getMyBookings(): List<LocalBooking>
    suspend fun getImage(url: String):Bitmap?
}