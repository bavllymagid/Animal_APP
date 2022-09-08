package com.evapharma.animalhealth.mainflow.utils

import android.annotation.SuppressLint
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object DateConverter {
    @SuppressLint("SimpleDateFormat")
    fun covertTimeToText(dataDate: String?): String {
        var convTime = ""
        val suffix = "Ago"
        try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val pasTime = dataDate?.let { dateFormat.parse(it) }
            val nowTime = Date()
            val dateDiff = nowTime.time - pasTime!!.time
            val second: Long = TimeUnit.MILLISECONDS.toSeconds(dateDiff)
            val minute: Long = TimeUnit.MILLISECONDS.toMinutes(dateDiff)
            val hour: Long = TimeUnit.MILLISECONDS.toHours(dateDiff)
            val day: Long = TimeUnit.MILLISECONDS.toDays(dateDiff)

            when{
                second < 60 -> convTime = "${second}Sec $suffix"
                minute < 60 ->  convTime = "${minute}min $suffix"
                hour < 24 ->    convTime = "${hour}h $suffix"
                day < 7 ->   convTime = "${day}Days $suffix"
                day >= 7 ->{
                    convTime = if (day > 360) {
                        (day / 360).toString() + "Years " + suffix
                    } else if (day > 30) {
                        (day / 30).toString() + "Months " + suffix
                    } else {
                        (day / 7).toString() + "Week " + suffix
                    }
                }
            }
        } catch (e: ParseException) {
            e.printStackTrace()
            Log.e("ConvTimeE", e.message.toString())
        }
        return convTime
    }

    @SuppressLint("SimpleDateFormat")
    fun stringToDate(date:String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
            .parse(date)
        val localDate = dateFormat?.let { SimpleDateFormat("yyyy-MM-dd").format(it) }
        return localDate.toString()
    }

    @SuppressLint("SimpleDateFormat")
    fun stringToTime(date: String):String{
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
            .parse(date)
        println("Date$dateFormat")
        val tk = StringTokenizer(dateFormat.toString())
        val day = tk.nextToken()
        val dayCal = tk.nextToken()
        val dayCalNum = tk.nextToken()
        val time = tk.nextToken()

        val rawTime = SimpleDateFormat("hh:mm:ss")
        val timeFormat = SimpleDateFormat("hh:mm a")

        return try {
            val dt = rawTime.parse(time)
            return "Time Display: " + dt?.let { timeFormat.format(it) }
        } catch (e: ParseException) {
            ""
        }
    }
}