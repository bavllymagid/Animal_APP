package com.evapharma.animalhealth.mainflow.booking.data.remote

import com.evapharma.animalhealth.mainflow.booking.domain.model.AppointmentModel
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingModel
import com.evapharma.animalhealth.mainflow.booking.domain.model.DateTimeSlot
import com.evapharma.animalhealth.mainflow.booking.domain.model.DoctorModel
import retrofit2.Response
import retrofit2.http.*

interface BookingApi {

    @GET("Doctor/GetAllDoctors")
    fun getDoctorsList(@Query("counter") pageNum:Int) : Response<List<DoctorModel>>

    @GET("Doctors/Days")
    fun getDoctorDays(@Query("doctorId") doctorId:String) : Response<List<DateTimeSlot>>

    @GET("ScheduleSlot/GetAllSlots")
    fun getDoctorsTime(@Query("doctorId") doctorId:String , @Query("day") date:String) : Response<List<DateTimeSlot>>

    @POST("Appointment/addAppointment")
    fun sendDoctorAppointment(@Body appointment: AppointmentModel) : Response<Boolean>

    @GET("Appointment/BookingHistory")
    fun getMyBookings(@Query("CustomerId") customerId:String, @Query("counter") pageNum: Int):Response<List<BookingModel>>

}