package com.evapharma.animalhealth.mainflow.booking.data.mappers

import com.evapharma.animalhealth.mainflow.booking.data.local.model.LocalBooking
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingModel
import com.evapharma.animalhealth.mainflow.booking.domain.model.DoctorModel
import com.google.gson.Gson

object LocalToBooking {
    fun localToBooking(localBooking: List<LocalBooking>) : List<BookingModel>{
        val list = ArrayList<BookingModel>()
        for(item in localBooking){
            val doctor = Gson().fromJson(item.doctor, DoctorModel::class.java)
            list.add(BookingModel(item.appointmentId, item.date,doctor,item.isCancelled,
            item.isFollowUp,item.price,item.slotId))
        }

        return list
    }

}