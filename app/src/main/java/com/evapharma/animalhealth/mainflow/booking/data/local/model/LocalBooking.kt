package com.evapharma.animalhealth.mainflow.booking.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalBooking(
    val IsCancelled: Boolean,
    val IsFollowUp: Boolean,
    val Price: Int,
    @PrimaryKey
    val SlotId: Int,
    val doctor: String,
    val appointment : String
)