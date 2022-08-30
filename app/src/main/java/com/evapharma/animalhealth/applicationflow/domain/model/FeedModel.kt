package com.evapharma.animalhealth.applicationflow.domain.model

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class FeedModel(val id:String, val text:String, val image: Bitmap?, val date: String):Parcelable
