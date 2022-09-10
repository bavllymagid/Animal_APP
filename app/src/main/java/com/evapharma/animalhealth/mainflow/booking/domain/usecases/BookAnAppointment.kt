package com.evapharma.animalhealth.mainflow.booking.domain.usecases

import com.evapharma.animalhealth.mainflow.booking.domain.model.AppointmentModel
import com.evapharma.animalhealth.mainflow.booking.domain.repository.BookingRepository
import javax.inject.Inject

class BookAnAppointment @Inject constructor(val repository: BookingRepository) {
    suspend fun execute(token:String, appointment: AppointmentModel) : Boolean{
        return repository.sendDoctorAppointment(token, appointment)
    }
}