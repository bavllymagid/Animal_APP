package com.evapharma.animalhealth.mainflow.booking.data.local

import com.evapharma.animalhealth.mainflow.booking.data.local.model.LocalBooking
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingModel
import javax.inject.Inject

class BookingLocalDataSourceImpl @Inject constructor(private val db:BookingDao) : BookingLocalDataSource {
    override suspend fun cacheBookings(bookingList: List<LocalBooking>) {
        db.cacheBookings(bookingList)
    }

    override suspend fun getBookings(): List<LocalBooking> {
        return db.getBookings()
    }

    override suspend fun deleteData() {
        db.dropTable()
    }

}