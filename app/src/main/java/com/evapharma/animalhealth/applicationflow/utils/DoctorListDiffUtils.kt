package com.evapharma.animalhealth.applicationflow.utils

import androidx.recyclerview.widget.DiffUtil
import com.evapharma.animalhealth.applicationflow.domain.model.DoctorModel


class DoctorListDiffUtils  : DiffUtil.ItemCallback<DoctorModel>(){
    override fun areItemsTheSame(oldItem: DoctorModel, newItem: DoctorModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DoctorModel, newItem: DoctorModel): Boolean {
        return oldItem == newItem
    }
}
