package com.evapharma.animalhealth.mainflow.booking.utils

import androidx.recyclerview.widget.DiffUtil
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingModel

class BookingDiffUtils : DiffUtil.ItemCallback<BookingModel>() {
    override fun areItemsTheSame(oldItem: BookingModel, newItem: BookingModel): Boolean {
        return oldItem.appointmentId == newItem.appointmentId
    }

    override fun areContentsTheSame(oldItem: BookingModel, newItem: BookingModel): Boolean {
        return oldItem == newItem
    }
}