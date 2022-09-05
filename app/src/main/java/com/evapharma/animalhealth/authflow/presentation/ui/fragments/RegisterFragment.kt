package com.evapharma.animalhealth.authflow.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.authflow.domain.model.CustomerModel
import com.evapharma.animalhealth.authflow.presentation.viewmodel.ViewModel
import com.evapharma.animalhealth.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        val viewModel: ViewModel = ViewModelProvider(this).get(ViewModel::class.java)

        binding.RegisterBtn.setOnClickListener {
            val customer = CustomerModel(binding.nameInput.toString(), binding.mobileInput.toString(), binding.PasswordInput.toString())
            viewModel.registerCustomer(customer)
            findNavController().navigate(R.id.action_registerFragment_to_otpFragment)
        }
        return binding.root
    }
}