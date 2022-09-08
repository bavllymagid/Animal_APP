package com.evapharma.animalhealth.mainflow.booking.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.evapharma.animalhealth.mainflow.booking.utils.DoctorListDiffUtils
import com.evapharma.animalhealth.databinding.DoctorCardItemBinding
import com.evapharma.animalhealth.mainflow.booking.domain.model.DoctorModel
import com.evapharma.animalhealth.mainflow.utils.DateConverter

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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DoctorsViewHolder, position: Int) {
        val doctor = getItem(position)
        holder.binding.apply {
            doctorNameTv.text = doctor.userName
//            profileImage.setImageBitmap(doctor.photo)
            try{
                timeTv.text = "${DateConverter.stringToMonth(doctor.nearestSlot.startAt)} ${
                    DateConverter.stringToTime(doctor.nearestSlot.startAt)
                }"
            }catch (e:Exception){
                e.toString()
                timeTv.text = "UNKNOWN"
            }
            specializationTv.text = doctor.specialization
            feesTv.text = "${doctor.fees} EGP"
            bookAppointmentBtn.setOnClickListener{
                onDoctorSelected.onBookAppointmentClicked(doctor)
            }
        }
    }


    interface OnDoctorSelected {
        fun onBookAppointmentClicked(doctor:DoctorModel)
    }

}