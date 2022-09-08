package com.evapharma.animalhealth.mainflow.booking.domain.usecases

import com.evapharma.animalhealth.mainflow.booking.domain.repository.BookingRepository
import javax.inject.Inject

class GetImageFromApi @Inject constructor(repository: BookingRepository) {
    suspend fun execute(url:String){

    }
}