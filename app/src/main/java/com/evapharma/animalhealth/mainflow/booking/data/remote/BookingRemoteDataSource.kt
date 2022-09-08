package com.evapharma.animalhealth.mainflow.booking.data.remote

import com.evapharma.animalhealth.mainflow.booking.domain.model.AppointmentModel
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingModel
import com.evapharma.animalhealth.mainflow.booking.domain.model.DateTimeSlot
import com.evapharma.animalhealth.mainflow.booking.domain.model.DoctorModel
import java.sql.Time
import java.util.*

interface BookingRemoteDataSource {
    suspend fun getDoctorList(pageNum:Int):List<DoctorModel>
    suspend fun getDoctorDays(id:String):List<DateTimeSlot>
    suspend fun getDoctorsTime(id:String, day:String): List<DateTimeSlot>
    suspend fun sendDoctorAppointment(appointment: AppointmentModel) : Boolean
    suspend fun getBookings(id:String, pageNum:Int) : List<BookingModel>
}