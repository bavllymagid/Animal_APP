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
import com.evapharma.animalhealth.mainflow.utils.DateConverter
import com.evapharma.animalhealth.utils.ImageLoader
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
    var bookingList = ArrayList<BookingModel>()

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

    private fun getUpComing(){
        CoroutineScope(Dispatchers.Main).launch {
            try{
                withContext(Dispatchers.IO){
                    bookingList = bookingViewModel.getUpComingBookings("Bearer ${userViewModel.getLocalToken()}") as ArrayList<BookingModel>
                }
                if(bookingList.isNotEmpty()){
                    getStartingSoon()
                    adapter.submitList(bookingList)
                    binding.progressBar.visibility = View.GONE
                    binding.nestedScrollView.visibility = View.VISIBLE
                }else{
                    binding.noDataUpcoming.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.nestedScrollView.visibility = View.VISIBLE
                }
            }catch (e:Exception){
                binding.progressBar.visibility = View.GONE
                binding.nestedScrollView.visibility = View.VISIBLE
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

    private fun getStartingSoon(){
        for(item in bookingList){
            if(DateConverter.timeComparator(item.date)){
                ImageLoader.loadImageIntoImageView(item.doctor.image?:"",binding.profileImage2)
                binding.NameTextview1.text = item.doctor.userName
                binding.hintTextview2.text = item.doctor.specialization
                binding.date.text = "${DateConverter.stringToMonth(item.date)}|${DateConverter.stringToTime(item.date)}"
                return
            }
        }
        binding.cardView.visibility = View.GONE
        binding.noDataStartingSoon.visibility = View.VISIBLE
    }

}