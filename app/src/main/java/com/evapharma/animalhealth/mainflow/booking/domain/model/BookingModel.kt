package com.evapharma.animalhealth.mainflow.booking.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nullable

@Entity
class BookingModel(
    val IsCancelled: Boolean,
    val IsFollowUp: Boolean,
    val Price: Int,
    @PrimaryKey
    val SlotId: Int,
    val doctor: String,
    val appointment : String
)