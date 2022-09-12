package com.evapharma.animalhealth.mainflow.booking.data.repository

import com.evapharma.animalhealth.mainflow.booking.data.local.BookingLocalDataSource
import com.evapharma.animalhealth.mainflow.booking.data.local.model.LocalBooking
import com.evapharma.animalhealth.mainflow.booking.data.remote.BookingRemoteDataSource
import com.evapharma.animalhealth.mainflow.booking.domain.model.*
import com.evapharma.animalhealth.mainflow.booking.domain.repository.BookingRepository
import javax.inject.Inject

class BookingRepositoryImpl @Inject constructor(val remoteDataSource: BookingRemoteDataSource,
                                                private val localDataSource: BookingLocalDataSource) : BookingRepository {
    override suspend fun getDoctorList(keyword:String, pageNum: Int): DoctorModelX? {
        return remoteDataSource.getDoctorList(keyword,pageNum)
    }

    override suspend fun getDoctorDays(id: String): List<String> {
        return remoteDataSource.getDoctorDays(id)
    }

    override suspend fun getDoctorsTime(id: String, day: String): List<DateTimeSlot> {
        return remoteDataSource.getDoctorsTime(id, day)
    }

    override suspend fun sendDoctorAppointment(token:String, appointment: AppointmentModel): Boolean {
        return remoteDataSource.sendDoctorAppointment(token,appointment)
    }

    override suspend fun getBookingsRemote(id: String): List<BookingModel> {
        return remoteDataSource.getBookings(id)
    }


    override suspend fun cacheBookings(bookingList: List<LocalBooking>) {
        localDataSource.cacheBookings(bookingList)
    }

    override suspend fun getMyBookings(): List<LocalBooking> {
        return localDataSource.getBookings()
    }

    override suspend fun getPrevBookings(id: String, pageNum: Int): BookingList? {
        return remoteDataSource.getPreviousBookings(id,pageNum)
    }

    override suspend fun deleteLocal() {
        localDataSource.deleteData()
    }


}