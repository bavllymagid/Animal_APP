package com.evapharma.animalhealth.authflow.presentation.ui.fragments

import android.os.Bundle

import android.text.TextUtils.replace
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.authflow.domain.model.CustomerModel
import com.evapharma.animalhealth.authflow.presentation.viewmodel.AuthViewModel
import com.evapharma.animalhealth.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var registerViewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)

        registerViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        binding.RegisterBtn.setOnClickListener {
            val customer = CustomerModel(binding.nameInput.text.toString(), binding.mobileInput.text.toString(), binding.PasswordInputText.text.toString())
            Log.d("Customer Model", "Name: ${customer.UserName} ,  password: ${customer.Password}, number: ${customer.PhoneNumber}")
            CoroutineScope(Dispatchers.IO).launch {
                val response = registerViewModel.registerCustomer(customer)
                withContext(Dispatchers.Main){
                    val bundle = Bundle()
                    bundle.putParcelable("response", response)
//                    val otpFragment = OtpFragment()
//                    otpFragment.arguments = bundle
//                    transferTo(otpFragment)
                    Log.d("responseFlag:", response.isSuccess.toString())
                    Log.d("responseMessage", response.message)
                    findNavController().navigate(R.id.action_registerFragment_to_otpFragment, bundle)
                }
            }
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