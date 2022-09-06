package com.evapharma.animalhealth.authflow.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.authflow.domain.model.RegResponseModel

class OtpFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val response = arguments?.getParcelable<RegResponseModel>("response")

        Log.d("MyApp", "flag : ${response?.isSuccess} , body: ${response?.message}")

        return inflater.inflate(R.layout.fragment_otp, container, false)
    }

}