package com.evapharma.animalhealth.mainflow.booking.domain.model

data class AppointmentModel(
    val CustomerId: String,
    val DoctorId: String,
    val dateTimeSlot: DateTimeSlot
)