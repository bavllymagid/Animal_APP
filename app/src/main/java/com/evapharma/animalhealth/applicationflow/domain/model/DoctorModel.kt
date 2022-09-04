package com.evapharma.animalhealth.applicationflow.domain.model

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DoctorModel(val id:String, val name: String, val photo: Bitmap?, val timeAvailability: String):Parcelable
