package com.evapharma.animalhealth.mainflow.booking.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class BookingModel(
    @PrimaryKey
    val CustomerId: String,
    val DoctorId: String,
    val IsCancelled: Boolean,
    val IsFollowUp: Boolean,
    val Price: Int,
    val SlotId: Int
)