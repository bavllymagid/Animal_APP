package com.evapharma.animalhealth.mainflow.booking.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evapharma.animalhealth.databinding.TimeItemBinding
import com.evapharma.animalhealth.mainflow.utils.DateConverter

class TimeAdaptor(private val timeList: ArrayList<String>, private val onItemSelected: OnItemSelected) : RecyclerView.Adapter<TimeAdaptor.TimeViewHolder>(){

    class TimeViewHolder(val binding: TimeItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        return TimeViewHolder(
            TimeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        holder.binding.timeTv.text = timeList[holder.adapterPosition]
        holder.itemView.setOnClickListener{
            onItemSelected.onTimeSlotSelected(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return timeList.size
    }

    interface OnItemSelected{
        fun onTimeSlotSelected(position: Int)
    }
}