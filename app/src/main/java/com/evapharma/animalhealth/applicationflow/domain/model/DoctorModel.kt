package com.evapharma.animalhealth.applicationflow.domain.model

import android.graphics.Bitmap

data class DoctorModel(val id:String, val name: String, val photo: Bitmap?, val timeAvailability: String)
