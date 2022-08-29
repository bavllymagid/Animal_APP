package com.evapharma.animalhealth.applicationflow.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evapharma.animalhealth.databinding.FragmentBookAppointementBinding


class BookAppointmentFragment : Fragment() {


    lateinit var binding: FragmentBookAppointementBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookAppointementBinding.inflate(layoutInflater)
        return binding.root
    }

}