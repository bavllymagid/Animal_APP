package com.evapharma.animalhealth.authflow.presentation.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.authflow.domain.model.LoginModel
import com.evapharma.animalhealth.authflow.presentation.viewmodel.AuthViewModel
import com.evapharma.animalhealth.databinding.FragmentLoginBinding
import com.evapharma.animalhealth.mainflow.ApplicationActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: AuthViewModel
    private var authToken = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //inflating login fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        loginViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        //Going From The login Screen To the Home Screen Without account
        binding.SkipBtn.setOnClickListener()
        {
            val intent = Intent(this.requireContext(), ApplicationActivity::class.java)
            startActivity(intent)
            requireActivity().finishAffinity()
        }
        //Going From The login Screen To Register screen
        binding.RegisterBtn.setOnClickListener()
        {
            Navigation.findNavController(view!!).navigate(R.id.registerFragment)
        }
        binding.SignInBTN.setOnClickListener()
        {
            val mobileNumber = binding.phoneEdt.text.toString()
            if (mobileNumber.isBlank() || mobileNumber.isEmpty() || mobileNumber.length > 11) {
                binding.phoneEdt.error = "Please enter a valid mobile number"
            } else {
                val mobileService = mobileNumber.subSequence(0, 3)
                if (!(mobileService == "010" || mobileService == "011" || mobileService == "012" || mobileService == "015")
                ) {
                    binding.phoneEdt.error = "Please enter a valid mobile number"
                } else {
                    val password = binding.passwordEdt.text.toString()
                    if (!(containsNumber(password) && containsUpperCase(password) && containsUpperCase(
                            password
                        ) && password.length > 7)
                    ) {
                        binding.passwordEdt.error =
                            "Password should at least be of 8 characters and contains an upper case letter, a special character and a digit"
                    } else {
                        loginUser()
                    }
                }
            }
        }
        return binding.root
    }


    private fun containsUpperCase(password: String): Boolean {
        for (i: Int in password.indices) {
            if (password[i].isUpperCase()) {
                return true
            }
        }
        return false
    }

    private fun containsNumber(password: String): Boolean {
        for (i: Int in password.indices) {
            if (password[i].isDigit()) {
                return true
            }

        }
        return false
    }

    private fun loginUser() {
        val user =
            LoginModel(binding.passwordEdt.text.toString(),binding.phoneEdt.text.toString())
        CoroutineScope(Dispatchers.Main).launch {
            try {
                withContext(Dispatchers.IO) {
                    authToken = loginViewModel.getToken(user)

                    if (authToken == "") {
                        Snackbar.make(view!!, "The Account is not found", Snackbar.LENGTH_LONG).show()
                    } else {
                        val intent = Intent(context?.applicationContext, ApplicationActivity::class.java)
                        startActivity(intent)
                        requireActivity().finishAffinity()
                    }

                }
            } catch (e: Exception) {
                Snackbar.make(view!!, "Something Went Wrong Please Try Again Later", Snackbar.LENGTH_SHORT).show()
            }
        }
    }


}

