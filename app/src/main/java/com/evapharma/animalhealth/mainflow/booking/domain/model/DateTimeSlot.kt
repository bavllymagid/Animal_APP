package com.evapharma.animalhealth.mainflow.booking.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class DateTimeSlot(
    val dayWeek: Int,
    val dayWeekText: String,
    val doctorId: String,
    val endAt: String,
    val isReserverd: Boolean,
    val slotId: Int,
    val startAt: String
):Parcelable