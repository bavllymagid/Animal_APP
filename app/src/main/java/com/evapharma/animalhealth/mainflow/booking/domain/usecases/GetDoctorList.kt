package com.evapharma.animalhealth.mainflow.booking.domain.usecases

import com.evapharma.animalhealth.mainflow.booking.domain.model.DoctorModel
import com.evapharma.animalhealth.mainflow.booking.domain.repository.BookingRepository
import javax.inject.Inject

class GetDoctorList @Inject constructor(val repository: BookingRepository) {
    suspend fun execute(pageNum:Int) : List<DoctorModel>{
        return repository.getDoctorList(pageNum)
    }
}