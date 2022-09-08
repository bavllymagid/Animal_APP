package com.evapharma.animalhealth.authflow.presentation.ui.fragments

import android.os.Bundle

import android.text.TextUtils.replace
import android.util.Log
import android.view.Gravity

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.authflow.domain.model.CustomerModel
import com.evapharma.animalhealth.authflow.domain.model.RegResponseModel
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

                withContext(Dispatchers.Main){

//                    val otpFragment = OtpFragment()
//                    otpFragment.arguments = bundle
//                    transferTo(otpFragment)

                    if(binding.nameInput.text.toString().isBlank() || binding.nameInput.text.toString().isEmpty()){
                        binding.nameInput.setError("Please enter a valid username")
                    }
                    else{
                        val mobileNumber = binding.mobileInput.text.toString()
                        if(mobileNumber.isBlank() || mobileNumber.isEmpty()  || mobileNumber.length > 11){
                            binding.mobileInput.setError("Please enter a valid mobile number")
                        }
                        else{
                        val mobileService = mobileNumber.subSequence(0,3)
                        if(!(mobileService.equals("010") || mobileService.equals("011") || mobileService.equals("012") || mobileService.equals("015"))){
                            binding.mobileInput.setError("Please enter a valid mobile number")
                        }
                        else{
                            val password = binding.PasswordInputText.text.toString()
                            if(!(containsNumber(password) && containsUpperCase(password) && containsUpperCase(password) && password.length>7)){
                                binding.PasswordInputText.setError("Password should at least be of 8 characters and contains an upper case letter, a special character and a digit")
                            }
                            else{
                                var success = true
                                var myResponse = RegResponseModel(false, "not valid")
                                try {
                                    val response = registerViewModel.registerCustomer(customer)
                                    myResponse = response
                                }
                                catch (e: NullPointerException){
                                    success = false
                                    val toast = Toast.makeText(context, "Something went wrong. Check your internet connection", Toast.LENGTH_LONG)
                                    toast.show()
                                }
                                if(success){
                                    val bundle = Bundle()
                                    bundle.putParcelable("response", myResponse)
                                    Log.d("responseFlag:", myResponse.isSuccess.toString())
                                    Log.d("responseMessage", myResponse.message)
                                    findNavController().navigate(R.id.action_registerFragment_to_otpFragment, bundle)
                                }

                            }
                        }
                    }
                    }

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

    private fun containsUpperCase(password: String): Boolean{
        for (i:Int in password.indices){
            if(password.get(i).isUpperCase()){
                return true
            }
        }
        return false
    }

    private fun containsNumber(password: String): Boolean{
        for (i:Int in password.indices){
            if(password.get(i).isDigit()){
                return true
            }

        }
        return false
    }

    private fun containseSpecialCharacter(password: String):Boolean{
        for (i:Int in password.indices){
            if(!(password.get(i).isDigit() || password.get(i).isLetter() || password.get(i).isWhitespace())){
                return true
            }
        }
        return false
    }

}