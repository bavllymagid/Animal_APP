package com.evapharma.animalhealth.mainflow.booking.utils

import androidx.recyclerview.widget.DiffUtil
import com.evapharma.animalhealth.mainflow.booking.domain.model.DoctorModel


class DoctorListDiffUtils  : DiffUtil.ItemCallback<DoctorModel>(){
    override fun areItemsTheSame(oldItem: DoctorModel, newItem: DoctorModel): Boolean {
        return oldItem.doctorId == newItem.doctorId
    }

    override fun areContentsTheSame(oldItem: DoctorModel, newItem: DoctorModel): Boolean {
        return oldItem == newItem
    }
}
