package com.evapharma.animalhealth.authflow.presentation.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.work.impl.Schedulers.schedule
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.authflow.domain.model.RegResponseModel
import com.evapharma.animalhealth.databinding.CustomRegDialogBinding
import com.evapharma.animalhealth.databinding.FragmentOtpBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule

@AndroidEntryPoint
class OtpFragment : Fragment() {

    private lateinit var binding : FragmentOtpBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentOtpBinding.inflate(layoutInflater)

        val response = arguments?.getParcelable<RegResponseModel>("response")
        Log.d("MyApp", "flag : ${response?.isSuccess} , body: ${response?.message}")

        binding.VerifyBTN.setOnClickListener {
            val customDialog = CustomRegDialogBinding.inflate(layoutInflater)
            val customAlertDialog = AlertDialog.Builder(this.context).setView(customDialog.root).create()
            val num1 = binding.OTPfield1Text.text.toString()
            val num2 = binding.OTPfield2Text.text.toString()
            val num3 = binding.OTPfield3Text.text.toString()
            val num4 = binding.OTPfield4Text.text.toString()
            Log.d("numbers:", num1 + " " + num2 + " " + num3 + " " + num4)
            if(num1.isEmpty() || num1.isBlank() || num2.isEmpty() || num2.isBlank() || num3.isEmpty() || num3.isBlank() || num4.isEmpty() || num4.isBlank()){
                val toast = Toast.makeText(context, "Please fill all the required fields", Toast.LENGTH_LONG)
                toast.show()
            }
            else{
                if(num1 == "1" && num2 == "2" && num3 == "3" && num4 == "4"){
                    customDialog.registrationMsg.text = "Registration completed successfully!!"
                    customAlertDialog.show()
                    lifecycleScope.launch {
                        delay(3000)
                        findNavController().navigate(R.id.action_otpFragment_to_loginFragment)
                        customAlertDialog.dismiss()
                    }
                }
                else{
                    Log.d("incorrect numbers:", num1 + " " + num2 + " " + num3 + " " + num4)
                    val toast = Toast.makeText(context, "Incorrect OTP", Toast.LENGTH_LONG)
                    toast.show()
                }
            }

        }



        return binding.root
    }

}