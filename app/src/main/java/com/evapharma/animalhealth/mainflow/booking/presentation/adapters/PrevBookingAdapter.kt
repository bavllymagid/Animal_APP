package com.evapharma.animalhealth.mainflow.booking.presentation.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.evapharma.animalhealth.databinding.BookItemBinding
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingModel
import com.evapharma.animalhealth.mainflow.booking.utils.BookingDiffUtils
import com.evapharma.animalhealth.mainflow.utils.DateConverter
import com.evapharma.animalhealth.utils.ImageLoader

class PrevBookingAdapter:
    ListAdapter<BookingModel, PrevBookingAdapter.PrevBookingViewHolder>(BookingDiffUtils()) {

    class PrevBookingViewHolder(val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrevBookingViewHolder {
        return PrevBookingViewHolder(
            BookItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PrevBookingViewHolder, position: Int) {
        val booking = getItem(position)
        holder.binding.dateTv.text = "${DateConverter.stringToMonth(booking.date)} ${DateConverter.stringToTime(booking.date)}"
        holder.binding.nameTv.text = booking.doctor.userName
        if(booking.isCancelled){
            holder.binding.statusTv.text = "Cancelled"
            holder.binding.statusTv.setTextColor(Color.RED)
        }
        holder.binding.specialization.text = booking.doctor.specialization
        holder.binding.priceTv.text = booking.price.toString() + " EGP"
        ImageLoader.loadImageIntoImageView(booking.doctor.image?:"",holder.binding.profileImage)
    }
}