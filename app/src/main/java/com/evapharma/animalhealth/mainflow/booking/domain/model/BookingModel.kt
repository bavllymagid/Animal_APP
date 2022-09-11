package com.evapharma.animalhealth.mainflow.booking.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


data class BookingModel(
    val appointmentId: Int,
    val date: String,
    val doctor: DoctorModel,
    val isCancelled: Boolean,
    val isFollowUp: Boolean,
    val price: Int,
    val slotId: Int
)