package com.evapharma.animalhealth.mainflow.utils

import android.annotation.SuppressLint
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

object DateConverter {
    @SuppressLint("SimpleDateFormat")
    fun covertTimeToText(dataDate: String?): String {
        var convTime =  "Time Not Found"
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
                second < 60 -> convTime = "${second}Sec"
                minute < 60 ->  convTime = "${minute}min"
                hour < 24 ->    convTime = "${hour}h"
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
        return try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .parse(date)
            val localDate = dateFormat?.let { SimpleDateFormat("yyyy-MM-dd").format(it) }
            localDate.toString()
        }catch (e:Exception){
            "Time Not Found"
        }
    }


    fun listToStringDate(list : List<String>):ArrayList<String>{
        val tempList = ArrayList<String>()
        for(item in list){
            tempList.add(stringToDate(item))
        }
        return tempList
    }

    @SuppressLint("SimpleDateFormat")
    fun stringToMonth(date: String):String{
        return try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                .parse(date)
            println("Date$dateFormat")
            val tk = StringTokenizer(dateFormat?.toString() ?: "a b c d")
            val day = tk.nextToken()
            val month = tk.nextToken()
            val dayCalNum = tk.nextToken()
            val time = tk.nextToken()
            return "$dayCalNum $month"
        } catch (e: ParseException) {
            "Time Not Found"
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun stringToTime(date: String): String {
        return try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .parse(date)
            println("Date$dateFormat")
            val tk = StringTokenizer(dateFormat.toString())
            val day = tk.nextToken()
            val dayCal = tk.nextToken()
            val dayCalNum = tk.nextToken()
            val time = tk.nextToken()

            val rawTime = SimpleDateFormat("hh:mm:ss")
            val timeFormat = SimpleDateFormat("hh:mm a")


            val dt = rawTime.parse(time)
            return dt?.let { timeFormat.format(it) }.toString()
        } catch (e: ParseException) {
            "Time Not Found"
        }
    }
}