package com.evapharma.animalhealth.mainflow.booking.data.local

import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingModel
import javax.inject.Inject

class BookingLocalDataSourceImpl @Inject constructor(private val db:BookingDao) : BookingLocalDataSource {
    override suspend fun cacheBookings(bookingList: List<BookingModel>) {
        db.cacheBookings(bookingList)
    }

    override suspend fun getBookings(): List<BookingModel> {
        return db.getBookings()
    }

}