package com.evapharma.animalhealth.authflow.presentation.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.work.impl.Schedulers.schedule
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.authflow.domain.model.RegResponseModel
import com.evapharma.animalhealth.databinding.FragmentOtpBinding
import com.evapharma.animalhealth.databinding.RegistrationCustomDialogBinding
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
//        val successFlag:Boolean = (response!!).isSuccess
        binding.VerifyBTN.setOnClickListener {
//            Log.d("first", binding.OTPfield1.editText.toString())
            val customDialog = RegistrationCustomDialogBinding.inflate(layoutInflater)
            val customAlertDialog = AlertDialog.Builder(this.context).setView(customDialog.root).create()
            if(response?.isSuccess == true){
                Log.d("here", "henaa")
                customDialog.registrationMsg.setText("Registration Successful!")
                customAlertDialog.show()
//                lifecycleScope.launch {
//                    delay(2000)
//                    findNavController().navigate(R.id.action_otpFragment_to_loginFragment)
//                    customAlertDialog.dismiss()
//                }
                Timer().schedule(2000) {
//                    println("1 second elapsed")
                    findNavController().navigate(R.id.action_otpFragment_to_loginFragment)
                    customAlertDialog.dismiss()
                }
            }
            else{
                customDialog.registrationMsg.setText("Something went wrong. Please register again")
                customAlertDialog.show()
//                lifecycleScope.launch {
//                    delay(2000)
//                    findNavController().navigate(R.id.action_otpFragment_to_loginFragment)
//                    customAlertDialog.dismiss()
//                }
                Timer().schedule(2000) {
                    findNavController().navigate(R.id.action_otpFragment_to_loginFragment)
                    customAlertDialog.dismiss()
                }

                }

        }



        return inflater.inflate(R.layout.fragment_otp, container, false)
    }

}