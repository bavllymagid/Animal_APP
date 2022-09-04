package com.evapharma.animalhealth.applicationflow.presentation.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.applicationflow.domain.model.FeedModel
import com.evapharma.animalhealth.applicationflow.utils.DoctorListDiffUtils
import com.evapharma.animalhealth.applicationflow.utils.FeedDiffUtils
import com.evapharma.animalhealth.databinding.TimeItemBinding

class TimeAdaptor(private val timeList:ArrayList<String>, private val onItemSelected: OnItemSelected) : RecyclerView.Adapter<TimeAdaptor.TimeViewHolder>(){

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