package com.evapharma.animalhealth.applicationflow.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.applicationflow.domain.model.DoctorModel
import com.evapharma.animalhealth.applicationflow.presentation.adapters.DoctorListAdapter
import com.evapharma.animalhealth.databinding.FragmentSelectDoctorBinding


class SelectDoctorFragment : Fragment() , DoctorListAdapter.OnDoctorSelected{

    lateinit var binding: FragmentSelectDoctorBinding
    lateinit var adapter: DoctorListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectDoctorBinding.inflate(layoutInflater)

        adapter = DoctorListAdapter(this)

        val list = listOf(DoctorModel("1", "Dr.fahmy mohamed", null, "tomorrow"),
            DoctorModel("2", "Dr.fahmy mohamed", null, "tomorrow"),
            DoctorModel("3", "Dr.fahmy mohamed", null, "tomorrow"),
            DoctorModel("4", "Dr.fahmy mohamed", null, "tomorrow"))

        adapter.submitList(list)
        binding.doctorsList.adapter = adapter

        binding.backBtn.setOnClickListener{
            activity?.supportFragmentManager?.popBackStackImmediate()
        }

        return binding.root
    }

    override fun onBookAppointmentClicked(doctor: DoctorModel) {
        val bookAppointmentFragment = BookAppointmentFragment()
        requireActivity().supportFragmentManager.commit {
            addToBackStack(this.toString())
            replace(R.id.nav_container, bookAppointmentFragment)
        }
    }

}