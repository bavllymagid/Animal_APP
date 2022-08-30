package com.evapharma.animalhealth.applicationflow.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.evapharma.animalhealth.applicationflow.domain.model.DoctorModel
import com.evapharma.animalhealth.applicationflow.domain.model.FeedModel
import com.evapharma.animalhealth.applicationflow.utils.DoctorListDiffUtils
import com.evapharma.animalhealth.applicationflow.utils.FeedDiffUtils
import com.evapharma.animalhealth.databinding.DoctorCardItemBinding
import com.evapharma.animalhealth.databinding.FeedItemBinding

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
            doctorNameTv.text = doctor.name
//            profileImage.setImageBitmap(doctor.photo)
            timeTv.text = doctor.timeAvailability
            bookAppointmentBtn.setOnClickListener{
                onDoctorSelected.onBookAppointmentClicked(doctor)
            }
        }
    }


    interface OnDoctorSelected {
        fun onBookAppointmentClicked(doctor:DoctorModel)
    }

}