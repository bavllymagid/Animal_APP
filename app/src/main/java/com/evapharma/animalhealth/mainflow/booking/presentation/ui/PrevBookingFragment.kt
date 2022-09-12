package com.evapharma.animalhealth.mainflow.booking.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.authflow.presentation.viewmodel.AuthViewModel
import com.evapharma.animalhealth.databinding.FragmentPrevBookingBinding
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingList
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingModel
import com.evapharma.animalhealth.mainflow.booking.presentation.adapters.MyBookingsAdapter
import com.evapharma.animalhealth.mainflow.booking.presentation.adapters.PrevBookingAdapter
import com.evapharma.animalhealth.mainflow.booking.presentation.viewmodel.BookingViewModel
import com.evapharma.animalhealth.mainflow.feed.domain.model.PostsRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class PrevBookingFragment : Fragment() {

    lateinit var binding: FragmentPrevBookingBinding
    lateinit var adapter: PrevBookingAdapter
    lateinit var bookingViewModel: BookingViewModel
    lateinit var userViewModel:AuthViewModel
    lateinit var postsRequest: PostsRequest

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPrevBookingBinding.inflate(layoutInflater)
        bookingViewModel = ViewModelProvider(this)[BookingViewModel::class.java]
        userViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        adapter = PrevBookingAdapter()
        postsRequest = PostsRequest(1,"",1)

        binding.bookingsRc.adapter = adapter
        getPrevBookings()

        binding.backBtn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }


        binding.bookingsRc.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!binding.bookingsRc.canScrollVertically(1)){
                    if(postsRequest.page < postsRequest.maxPage){
                        binding.refreshBar.visibility = View.VISIBLE
                        postsRequest.page += 1
                        getPaginatedPrevBookings()
                    }else{
                        binding.refreshBar.visibility = View.GONE
                    }
                }
            }
        })

        return binding.root
    }

    private fun getPrevBookings(){
        CoroutineScope(Dispatchers.Main).launch {
            try{
                val list:BookingList
                withContext(Dispatchers.IO){
                    list = bookingViewModel.getPrevBookings("Bearer ${userViewModel.getLocalToken()}", postsRequest.page)!!
                    postsRequest.maxPage = list.maxPage
                    postsRequest.page = list.pageNumber
                }
                adapter.submitList(list.appointmentDetails)
                binding.progressBar.visibility = View.GONE
            }catch (e:Exception){
                binding.progressBar.visibility = View.GONE
                binding.noHistory.visibility = View.VISIBLE
                println(e.message.toString())
            }
        }
    }

    private fun getPaginatedPrevBookings(){
        CoroutineScope(Dispatchers.Main).launch {
            try{
                val list:BookingList
                withContext(Dispatchers.IO){
                    list = bookingViewModel.getPrevBookings("Bearer ${userViewModel.getLocalToken()}", postsRequest.page)!!
                    postsRequest.maxPage = list.maxPage
                    postsRequest.page = list.pageNumber
                }
                adapter.submitList(adapter.currentList + list.appointmentDetails)
                binding.refreshBar.visibility = View.GONE
            }catch (e:Exception){
                binding.refreshBar.visibility = View.GONE
                println(e.message.toString())
            }
        }
    }


}