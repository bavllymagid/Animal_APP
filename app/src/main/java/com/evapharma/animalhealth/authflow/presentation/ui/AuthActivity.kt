package com.evapharma.animalhealth.authflow.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    lateinit var  auth_Binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(auth_Binding.root)

    }
}