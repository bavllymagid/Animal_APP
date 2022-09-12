package com.evapharma.animalhealth.mainflow.booking.domain.usecases

import com.evapharma.animalhealth.mainflow.booking.data.mappers.BookingToLocal
import com.evapharma.animalhealth.mainflow.booking.data.mappers.LocalToBooking
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingList
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingModel
import com.evapharma.animalhealth.mainflow.booking.domain.repository.BookingRepository
import javax.inject.Inject

class GetBookings @Inject constructor(val repository: BookingRepository) {
    suspend fun execute(id:String) : List<BookingModel>{
        return try{
            val tryList = BookingToLocal.bookingToLocal(repository.getBookingsRemote(id))
            repository.deleteLocal()
            repository.cacheBookings(tryList)
            val list = LocalToBooking.localToBooking(repository.getMyBookings())
            list
        }catch (e:Exception){
            val list = LocalToBooking.localToBooking(repository.getMyBookings())
            list
        }
    }

    suspend fun executePrev(id: String, pageNum:Int):BookingList? {
        return repository.getPrevBookings(id,pageNum)
    }
}