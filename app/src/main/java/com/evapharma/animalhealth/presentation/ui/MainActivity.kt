package com.evapharma.animalhealth.presentation.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.evapharma.animalhealth.authflow.presentation.ui.AuthActivity
import com.evapharma.animalhealth.databinding.ActivityMainBinding
import com.evapharma.animalhealth.databinding.SomethingWentWrongAlertLayoutBinding
import com.evapharma.animalhealth.mainflow.ApplicationActivity
import com.evapharma.animalhealth.mainflow.booking.presentation.viewmodel.BookingViewModel
import com.evapharma.animalhealth.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var customDialog: SomethingWentWrongAlertLayoutBinding
    lateinit var alertDialog: AlertDialog

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        customDialog = SomethingWentWrongAlertLayoutBinding.inflate(layoutInflater)
        val customAlertDialog = AlertDialog.Builder(this).setView(customDialog.root).create()

        customDialog.refreshBtn.setOnClickListener {
            customAlertDialog.dismiss()
            getTokenFetch(customAlertDialog)
        }

        customAlertDialog.setCanceledOnTouchOutside(false)
        customAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        getTokenFetch(customAlertDialog)

    }

    private fun getTokenFetch(customAlertDialog: AlertDialog) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val token = getSharedPreferences("User", MODE_PRIVATE).getString("User", "")!!
                var newToken = token
                withContext(Dispatchers.IO) {
                    newToken = viewModel.getToken(token) ?: ""
                }
                if (newToken != "") {
                    val mIntent = Intent(applicationContext, ApplicationActivity::class.java)
                    startActivity(mIntent)
                    finishAffinity()
                }else if(newToken == "404"){
                    throw Throwable()
                }
                else {
                    getSharedPreferences("User", MODE_PRIVATE).edit().clear().apply()
                    val mIntent = Intent(applicationContext, AuthActivity::class.java)
                    startActivity(mIntent)
                    finishAffinity()
                }
            } catch (e: Exception) {
                customAlertDialog.show()
//                Snackbar.make(binding.root, "Something Went Wrong", Snackbar.LENGTH_LONG).show()
            }
        }
    }

}
