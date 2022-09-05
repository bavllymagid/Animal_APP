package com.evapharma.animalhealth.mainflow.booking.data.remote

import com.evapharma.animalhealth.mainflow.booking.domain.model.DoctorModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import java.sql.Time
import java.util.*

interface BookingRemoteInterface {

    @GET("doctors")
    fun getDoctorsList(@Header("Authorization") authToken: String) : List<DoctorModel>

    @GET("Doctors/Days")
    fun getDoctorDays(@Header("Authorization") authToken: String,
                      @Path("id") id:String) : List<Date>

    @GET("Doctors/time")
    fun getDoctorsTime(@Header("Authorization") authToken: String,
                       @Path("id") id:String,
                       @Path("day") day:String) : List<Time>

    @POST("Doctors/schedule")
    fun sendDoctorAppointment(@Header("Authorization") authToken: String,
                              @Path("id") id:String,
                              @Path("day") day:String,
                              @Path("time") time:String) : Boolean

}