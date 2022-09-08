package com.evapharma.animalhealth.mainflow.booking.data.remote

import com.evapharma.animalhealth.mainflow.booking.domain.model.*
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface BookingApi {

    @GET("Doctor/SearchDoctor")
    suspend fun getDoctorsList(@Query("Text") keyword:String, @Query("counter") pageNum:Int) : Response<DoctorModelX>

    @GET("ScheduleSlot/GetAllSlots")
    suspend fun getDoctorDays(@Query("doctorId") doctorId:String) : Response<List<DateTimeSlot>>

    @GET("ScheduleSlot/GetAllSlots")
    suspend fun getDoctorsTime(@Query("doctorId") doctorId:String , @Query("day") date:String) : Response<List<DateTimeSlot>>

    @POST("Appointment/addAppointment")
    suspend fun sendDoctorAppointment(@Body appointment: AppointmentModel) : Response<Boolean>

    @GET("Appointment/BookingHistory")
    suspend fun getMyBookings(@Query("CustomerId") customerId:String, @Query("counter") pageNum: Int):Response<List<BookingModel>>

    @GET("{url}")
    suspend fun getImage(url: String):Response<ResponseBody>

}