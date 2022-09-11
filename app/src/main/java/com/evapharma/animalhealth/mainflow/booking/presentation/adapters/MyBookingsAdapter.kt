package com.evapharma.animalhealth.mainflow.booking.presentation.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.databinding.BookItemBinding
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingModel
import com.evapharma.animalhealth.mainflow.booking.utils.BookingDiffUtils
import com.evapharma.animalhealth.mainflow.utils.DateConverter

class MyBookingsAdapter():ListAdapter<BookingModel,MyBookingsAdapter.BookingViewHolder>(BookingDiffUtils()) {

    class BookingViewHolder(val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        return BookingViewHolder(BookItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val booking = getItem(position)
        holder.binding.dateTv.text = DateConverter.stringToMonth(booking.date)
        holder.binding.nameTv.text = booking.doctor.userName
        if(booking.isCancelled){
            holder.binding.statusTv.text = "Cancelled"
            holder.binding.statusTv.setTextColor(Color.RED)
        }else{
            holder.binding.statusTv.text = "Not Determined"
        }
        holder.binding.specialization.text = booking.doctor.userName
        holder.binding.priceTv.text = booking.price.toString()
        holder.binding.profileImage
    }
}