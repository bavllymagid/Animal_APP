package com.evapharma.animalhealth.mainflow.booking.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DoctorModel(
    val doctorId: String,
    val fees: Int,
    val image: String,
    @SerializedName("nearstSlot")
    val nearestSlot: DateTimeSlot,
    val specialization: String,
    val userName: String
):Parcelable