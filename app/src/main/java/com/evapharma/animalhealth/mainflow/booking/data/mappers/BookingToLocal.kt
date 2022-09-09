package com.evapharma.animalhealth.mainflow.booking.data.mappers

import com.evapharma.animalhealth.mainflow.booking.data.local.model.LocalBooking
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingModel
import com.google.gson.Gson

object BookingToLocal {
    fun bookingToLocal(booking: List<BookingModel>) : List<LocalBooking>{
        val list = ArrayList<LocalBooking>()
        for(item in booking){
            list.add(
                LocalBooking(item.IsCancelled, item.IsFollowUp,
                    item.Price, item.SlotId, objectToJson(item.doctor)
                    , objectToJson(item.appointment))
            )
        }
        return list
    }

    private fun objectToJson(value: Any?) = Gson().toJson(value)
}