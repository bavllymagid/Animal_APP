package com.evapharma.animalhealth.mainflow.booking.domain.repository

import com.evapharma.animalhealth.mainflow.booking.domain.model.AppointmentModel
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingModel
import com.evapharma.animalhealth.mainflow.booking.domain.model.DateTimeSlot
import com.evapharma.animalhealth.mainflow.booking.domain.model.DoctorModel

interface BookingRepository {
    suspend fun getDoctorList(pageNum:Int):List<DoctorModel>
    suspend fun getDoctorDays(id:String):List<DateTimeSlot>
    suspend fun getDoctorsTime(id:String, day:String): List<DateTimeSlot>
    suspend fun sendDoctorAppointment(appointment: AppointmentModel) : Boolean
    suspend fun getBookingsRemote(id:String, pageNum:Int) : List<BookingModel>
    suspend fun cacheBookings(bookingList:List<BookingModel>)
    suspend fun getMyBookings():List<BookingModel>
}