package com.evapharma.animalhealth.mainflow.booking.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object StringToDateAndTime {
    @SuppressLint("SimpleDateFormat")
    fun stringToDate(date:String): Date? {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        return dateFormat.parse(date)
    }
}