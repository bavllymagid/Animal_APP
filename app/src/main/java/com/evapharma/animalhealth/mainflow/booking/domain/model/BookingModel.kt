package com.evapharma.animalhealth.mainflow.booking.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


class BookingModel(
    val IsCancelled: Boolean,
    val IsFollowUp: Boolean,
    val Price: Int,
    val SlotId: Int,
    val doctor: DoctorModel,
    val appointment : DateTimeSlot
)