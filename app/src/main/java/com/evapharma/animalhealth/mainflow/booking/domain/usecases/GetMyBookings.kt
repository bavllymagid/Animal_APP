package com.evapharma.animalhealth.mainflow.booking.domain.usecases

import com.evapharma.animalhealth.mainflow.booking.data.mappers.BookingToLocal
import com.evapharma.animalhealth.mainflow.booking.data.mappers.LocalToBooking
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingModel
import com.evapharma.animalhealth.mainflow.booking.domain.repository.BookingRepository
import javax.inject.Inject

class GetMyBookings @Inject constructor(val repository: BookingRepository) {
    suspend fun execute(id:String, pageNumber: Int) : List<BookingModel>{
        repository.cacheBookings(BookingToLocal.bookingToLocal(repository.getBookingsRemote(id,pageNumber)))
        return LocalToBooking.localToBooking(repository.getMyBookings())
    }
}