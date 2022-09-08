package com.evapharma.animalhealth.mainflow.booking.domain.usecases

import com.evapharma.animalhealth.mainflow.booking.domain.model.DateTimeSlot
import com.evapharma.animalhealth.mainflow.booking.domain.repository.BookingRepository
import javax.inject.Inject

class GetDoctorsAvailDateTime @Inject constructor(val repository: BookingRepository){
    suspend fun executeDays(id:String) : List<DateTimeSlot>{
        return repository.getDoctorDays(id)
    }

    suspend fun executeTimeSlots(id:String,day:String) : List<DateTimeSlot>{
        return repository.getDoctorsTime(id,day)
    }
}