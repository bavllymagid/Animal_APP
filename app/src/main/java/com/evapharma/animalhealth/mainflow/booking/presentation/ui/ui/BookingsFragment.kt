package com.evapharma.animalhealth.mainflow.booking.presentation.ui.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evapharma.animalhealth.databinding.FragmentBookingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookingsFragment : Fragment() {


    lateinit var binding: FragmentBookingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookingsBinding.inflate(layoutInflater)
        return binding.root
    }

}