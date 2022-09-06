package com.evapharma.animalhealth.authflow.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.applicationflow.presentation.ui.fragments.SelectDoctorFragment
import com.evapharma.animalhealth.authflow.domain.model.CustomerModel
import com.evapharma.animalhealth.authflow.presentation.viewmodel.AuthViewModel
import com.evapharma.animalhealth.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    lateinit var registerViewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)

        registerViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        binding.RegisterBtn.setOnClickListener {
            val customer = CustomerModel(binding.nameInput.toString(), binding.mobileInput.toString(), binding.PasswordInput.toString())
            registerViewModel.registerCustomer(customer).observe(viewLifecycleOwner){
                val bundle = Bundle()
                bundle.putParcelable("response", it)
                val otpFragment = OtpFragment()
                otpFragment.arguments = bundle
                transferTo(otpFragment)
            }
            findNavController().navigate(R.id.action_registerFragment_to_otpFragment)
        }
        return binding.root
    }

    private fun transferTo(fragment: Fragment){
        requireActivity().supportFragmentManager.commit {
            addToBackStack(this.toString())
            replace(R.id.nav_container, fragment)
        }
    }
}