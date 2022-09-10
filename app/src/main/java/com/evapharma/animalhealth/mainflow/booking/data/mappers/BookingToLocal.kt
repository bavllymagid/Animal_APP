package com.evapharma.animalhealth.mainflow.booking.data.mappers

import com.evapharma.animalhealth.mainflow.booking.data.local.model.LocalBooking
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingModel
import com.google.gson.Gson

object BookingToLocal {
    fun bookingToLocal(booking: List<BookingModel>) : List<LocalBooking>{
        val list = ArrayList<LocalBooking>()
        for(item in booking){
            list.add(
                LocalBooking(item.appointmentId, item.date, objectToJson(item.doctor), item.isCancelled,
                item.isFollowUp,item.price,item.slotId)
            )
        }
        return list
    }

    private fun objectToJson(value: Any?) = Gson().toJson(value)
}