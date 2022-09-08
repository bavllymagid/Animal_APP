package com.evapharma.animalhealth.mainflow.booking.data.repository

import com.evapharma.animalhealth.mainflow.booking.data.local.BookingLocalDataSource
import com.evapharma.animalhealth.mainflow.booking.data.remote.BookingApi
import com.evapharma.animalhealth.mainflow.booking.data.remote.BookingRemoteDataSource
import com.evapharma.animalhealth.mainflow.booking.domain.model.*
import com.evapharma.animalhealth.mainflow.booking.domain.repository.BookingRepository
import javax.inject.Inject

class BookingRepositoryImpl @Inject constructor(val remoteDataSource: BookingRemoteDataSource,
                                                val localDataSource: BookingLocalDataSource) : BookingRepository {
    override suspend fun getDoctorList(pageNum: Int): DoctorModelX {
        return remoteDataSource.getDoctorList(pageNum)
    }

    override suspend fun getDoctorDays(id: String): List<DateTimeSlot> {
        return remoteDataSource.getDoctorDays(id)
    }

    override suspend fun getDoctorsTime(id: String, day: String): List<DateTimeSlot> {
        return remoteDataSource.getDoctorsTime(id, day)
    }

    override suspend fun sendDoctorAppointment(appointment: AppointmentModel): Boolean {
        return remoteDataSource.sendDoctorAppointment(appointment)
    }

    override suspend fun getBookingsRemote(id: String, pageNum: Int): List<BookingModel> {
        return remoteDataSource.getBookings(id, pageNum)
    }

    override suspend fun cacheBookings(bookingList: List<BookingModel>) {
        localDataSource.cacheBookings(bookingList)
    }

    override suspend fun getMyBookings(): List<BookingModel> {
        return localDataSource.getBookings()
    }

}