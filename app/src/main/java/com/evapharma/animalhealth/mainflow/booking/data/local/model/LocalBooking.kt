package com.evapharma.animalhealth.mainflow.booking.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalBooking(
    @PrimaryKey
    val appointmentId: Int,
    val date: String,
    val doctor: String,
    val isCancelled: Boolean,
    val isFollowUp: Boolean,
    val price: Int,
    val slotId: Int
)