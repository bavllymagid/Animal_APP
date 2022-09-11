package com.evapharma.animalhealth.mainflow.booking.presentation.ui

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.authflow.presentation.viewmodel.AuthViewModel
import com.evapharma.animalhealth.databinding.FragmentBookingsBinding
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingModel
import com.evapharma.animalhealth.mainflow.booking.presentation.adapters.MyBookingsAdapter
import com.evapharma.animalhealth.mainflow.booking.presentation.viewmodel.BookingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class BookingsFragment : Fragment() {


    lateinit var binding: FragmentBookingsBinding
    lateinit var bookingViewModel: BookingViewModel
    lateinit var adapter:MyBookingsAdapter
    lateinit var userViewModel:AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookingsBinding.inflate(layoutInflater)
        bookingViewModel = ViewModelProvider(this)[BookingViewModel::class.java]
        userViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        adapter = MyBookingsAdapter()

        binding.upcomingRc.adapter = adapter

        binding.previousBtn.setOnClickListener{
            transferTo(PrevBookingFragment())
        }

        getUpComing()

        return binding.root
    }

    fun getUpComing(){
        CoroutineScope(Dispatchers.Main).launch {
            try{
                val list:ArrayList<BookingModel>
                withContext(Dispatchers.IO){
                    list = bookingViewModel.getUpComingBookings("Bearer ${userViewModel.getLocalToken()}") as ArrayList<BookingModel>
                }
                adapter.submitList(list)
            }catch (e:Exception){
                e.toString()
            }
        }
    }

    private fun transferTo(fragment: Fragment) {
        requireActivity().supportFragmentManager.commit {
            addToBackStack(this.toString())
            replace(R.id.nav_container, fragment)
        }
    }

}