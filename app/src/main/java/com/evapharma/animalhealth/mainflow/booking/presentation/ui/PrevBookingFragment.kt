package com.evapharma.animalhealth.mainflow.booking.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.authflow.presentation.viewmodel.AuthViewModel
import com.evapharma.animalhealth.databinding.FragmentPrevBookingBinding
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingList
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingModel
import com.evapharma.animalhealth.mainflow.booking.presentation.adapters.MyBookingsAdapter
import com.evapharma.animalhealth.mainflow.booking.presentation.viewmodel.BookingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class PrevBookingFragment : Fragment() {

    lateinit var binding: FragmentPrevBookingBinding
    lateinit var adapter: MyBookingsAdapter
    lateinit var bookingViewModel: BookingViewModel
    lateinit var userViewModel:AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPrevBookingBinding.inflate(layoutInflater)
        bookingViewModel = ViewModelProvider(this)[BookingViewModel::class.java]
        userViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        adapter = MyBookingsAdapter()

        binding.bookingsRc.adapter = adapter
        getPrevBookings()

        return binding.root
    }

    fun getPrevBookings(){
        CoroutineScope(Dispatchers.Main).launch {
            try{
                val list:List<BookingModel>
                withContext(Dispatchers.IO){
                    list = bookingViewModel.getPrevBookings("Bearer ${userViewModel.getLocalToken()}", 1)?.appointmentDetails ?:ArrayList()
                }
                adapter.submitList(list)
                binding.progressBar.visibility = View.GONE
            }catch (e:Exception){
                binding.progressBar.visibility = View.GONE
                println(e.message.toString())
            }
        }
    }


}