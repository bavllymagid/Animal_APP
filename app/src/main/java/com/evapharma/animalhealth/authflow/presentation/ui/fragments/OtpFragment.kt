package com.evapharma.animalhealth.authflow.presentation.ui.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.work.impl.Schedulers.schedule
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.authflow.domain.model.RegResponseModel
import com.evapharma.animalhealth.authflow.presentation.ui.AuthActivity
import com.evapharma.animalhealth.databinding.CustomRegDialogBinding
import com.evapharma.animalhealth.databinding.FragmentOtpBinding
import com.evapharma.animalhealth.mainflow.ApplicationActivity
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

        val log = arguments?.getString("log")

        binding.VerifyBTN.setOnClickListener {
            val customDialog = CustomRegDialogBinding.inflate(layoutInflater)
            val customAlertDialog = AlertDialog.Builder(this.context).setView(customDialog.root).create()
            val num1 = binding.OTPfield1Text.text.toString()
            val num2 = binding.OTPfield2Text.text.toString()
            val num3 = binding.OTPfield3Text.text.toString()
            val num4 = binding.OTPfield4Text.text.toString()
            val num5 = binding.OTPfield5Text.text.toString()
            val num6 = binding.OTPfield6Text.text.toString()
            Log.d("numbers:", "$num1 $num2 $num3 $num4 $num5 $num6")
            if(num1.isEmpty() || num1.isBlank() || num2.isEmpty() || num2.isBlank() || num3.isEmpty() || num3.isBlank() || num4.isEmpty() || num4.isBlank()|| num5.isEmpty() || num5.isBlank()|| num6.isEmpty() || num6.isBlank()){
                val toast = Toast.makeText(context, "Please fill all the required fields", Toast.LENGTH_LONG)
                toast.show()
            }
            else{
                if(num1 == "1" && num2 == "2" && num3 == "3" && num4 == "4" && num5 == "5" && num6 == "6"){
                    if(log?.isEmpty() == true){
                        customDialog.registrationMsg.text = "Registration completed successfully!!"
                        customAlertDialog.show()
                        lifecycleScope.launch {
                            delay(3000)
                            findNavController().navigate(R.id.action_otpFragment_to_loginFragment)
                            customAlertDialog.dismiss()
                        }
                    }else{
                        val intent = Intent(requireActivity(), ApplicationActivity::class.java)
                        startActivity(intent)
                        requireActivity().finishAffinity()
                    }
                }
                else{
                    Log.d("incorrect numbers:", num1 + " " + num2 + " " + num3 + " " + num4)
                    val toast = Toast.makeText(context, "Incorrect OTP", Toast.LENGTH_LONG)
                    toast.show()
                }
            }

        }


        configOtpEditText(binding.OTPfield1Text,
            binding.OTPfield2Text,
            binding.OTPfield3Text,
            binding.OTPfield4Text,
            binding.OTPfield5Text,
            binding.OTPfield6Text)

        return binding.root
    }

    fun configOtpEditText(vararg etList: EditText) {
        val afterTextChanged = { index: Int, e: Editable? ->
            val view = etList[index]
            val text = e.toString()

            when (view.id) {
                // first text changed
                etList[0].id -> {
                    if (text.isNotEmpty()) etList[index + 1].requestFocus()
                }

                // las text changed
                etList[etList.size - 1].id -> {
                    if (text.isEmpty()) etList[index - 1].requestFocus()
                }

                // middle text changes
                else -> {
                    if (text.isNotEmpty()) etList[index + 1].requestFocus()
                    else etList[index - 1].requestFocus()
                }
            }
            false
        }
        etList.forEachIndexed { index, editText ->
            editText.doAfterTextChanged { afterTextChanged(index, it) }
        }
    }

}