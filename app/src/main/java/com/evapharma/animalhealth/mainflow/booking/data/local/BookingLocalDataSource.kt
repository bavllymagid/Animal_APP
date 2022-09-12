package com.evapharma.animalhealth.mainflow.booking.data.local

import androidx.room.Query
import com.evapharma.animalhealth.mainflow.booking.data.local.model.LocalBooking
import com.evapharma.animalhealth.mainflow.booking.domain.model.AppointmentModel
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingModel

interface BookingLocalDataSource {
    suspend fun cacheBookings(bookingList: List<LocalBooking>)
    suspend fun getBookings() : List<LocalBooking>
    suspend fun deleteData()
}