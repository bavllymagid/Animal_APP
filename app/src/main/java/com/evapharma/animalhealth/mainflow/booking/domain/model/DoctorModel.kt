package com.evapharma.animalhealth.mainflow.booking.domain.model

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DoctorModel(val id:String, val name: String, val photo: Bitmap?, val timeAvailability: String):Parcelable
