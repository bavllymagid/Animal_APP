package com.evapharma.animalhealth.mainflow.booking.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.evapharma.animalhealth.mainflow.booking.utils.DoctorListDiffUtils
import com.evapharma.animalhealth.databinding.DoctorCardItemBinding
import com.evapharma.animalhealth.mainflow.booking.domain.model.DoctorModel

class DoctorListAdapter(val onDoctorSelected: OnDoctorSelected) :
    ListAdapter<DoctorModel, DoctorListAdapter.DoctorsViewHolder>(DoctorListDiffUtils()) {

    class DoctorsViewHolder(val binding: DoctorCardItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DoctorListAdapter.DoctorsViewHolder {
        return DoctorsViewHolder(
            DoctorCardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DoctorsViewHolder, position: Int) {
        val doctor = getItem(position)
        holder.binding.apply {
            doctorNameTv.text = doctor.userName
//            profileImage.setImageBitmap(doctor.photo)
            timeTv.text = doctor.nearestSlot.startAt
            bookAppointmentBtn.setOnClickListener{
                onDoctorSelected.onBookAppointmentClicked(doctor)
            }
        }
    }


    interface OnDoctorSelected {
        fun onBookAppointmentClicked(doctor:DoctorModel)
    }

}