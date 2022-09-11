package com.evapharma.animalhealth.mainflow.booking.domain.usecases

import com.evapharma.animalhealth.mainflow.booking.data.mappers.BookingToLocal
import com.evapharma.animalhealth.mainflow.booking.data.mappers.LocalToBooking
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingList
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingModel
import com.evapharma.animalhealth.mainflow.booking.domain.repository.BookingRepository
import javax.inject.Inject

class GetBookings @Inject constructor(val repository: BookingRepository) {
    suspend fun execute(id:String) : List<BookingModel>{
        repository.cacheBookings(BookingToLocal.bookingToLocal(repository.getBookingsRemote(id)))
        return LocalToBooking.localToBooking(repository.getMyBookings())
    }

    suspend fun executePrev(id: String, pageNum:Int):BookingList? {
        return repository.getPrevBookings(id,pageNum)
    }
}