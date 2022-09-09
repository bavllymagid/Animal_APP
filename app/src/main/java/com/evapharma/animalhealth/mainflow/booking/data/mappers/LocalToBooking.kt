package com.evapharma.animalhealth.mainflow.booking.data.mappers

import com.evapharma.animalhealth.mainflow.booking.data.local.model.LocalBooking
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingModel
import com.evapharma.animalhealth.mainflow.booking.domain.model.DateTimeSlot
import com.evapharma.animalhealth.mainflow.booking.domain.model.DoctorModel
import com.google.gson.Gson

object LocalToBooking {
    fun localToBooking(localBooking: List<LocalBooking>) : List<BookingModel>{
        val list = ArrayList<BookingModel>()
        for(item in localBooking){
            val doctor = Gson().fromJson(item.doctor, DoctorModel::class.java)
            val slot = Gson().fromJson(item.appointment, DateTimeSlot::class.java)
            list.add(BookingModel(item.IsCancelled, item.IsFollowUp,
                item.Price, item.SlotId, doctor, slot))
        }

        return list
    }

}