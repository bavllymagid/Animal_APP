package com.evapharma.animalhealth.mainflow.booking.presentation.ui

import android.icu.util.Calendar
import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.mainflow.booking.presentation.adapters.TimeAdaptor
import com.evapharma.animalhealth.mainflow.ApplicationActivity
import com.evapharma.animalhealth.databinding.FragmentBookAppointementBinding
import com.evapharma.animalhealth.mainflow.booking.domain.model.DoctorModel
import com.evapharma.animalhealth.mainflow.booking.presentation.viewmodel.BookingViewModel
import com.evapharma.animalhealth.mainflow.feed.presentation.ui.FeedsFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

@AndroidEntryPoint
class BookAppointmentFragment : Fragment(){

    lateinit var binding: FragmentBookAppointementBinding
    lateinit var adapter: TimeAdaptor
    lateinit var appointmentViewModel: BookingViewModel
    lateinit var calendar: Calendar
    var cnt = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookAppointementBinding.inflate(layoutInflater)
        appointmentViewModel = ViewModelProvider(this)[BookingViewModel::class.java]
        binding.timeRc.visibility = View.GONE
        val doctor = arguments?.getParcelable<DoctorModel>("doctor")


        val items = listOf("10:30 AM", "11:00 PM", "12:00 AM", "1:00 PM", "5:00 AM")


        adapter = TimeAdaptor(ArrayList(items) , context?.applicationContext!!)

        binding.nextBtn.setOnClickListener{
            if(cnt == 0) {
                binding.calenderCard.visibility = View.INVISIBLE
                binding.timeRc.visibility = View.VISIBLE
                binding.nextBtn.setImageResource(R.drawable.right)
                cnt++
            }else{
                transferTo(FeedsFragment())
            }
        }

        calendar = Calendar.getInstance()
        if (doctor != null) {
            getDoctorDays(doctor)
        }

        binding.timeRc.adapter = adapter


        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                binding.calenderCard.visibility = View.VISIBLE
                binding.timeRc.visibility = View.GONE
                binding.nextBtn.setImageResource(R.drawable.arrow)
                cnt = 0
            }
        })

        binding.apply {
            doctorNameTv.text = doctor?.userName
        }

        binding.customToolbar.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.exit -> {transferTo(SelectDoctorFragment())}
            }
            return@setOnMenuItemClickListener false
        }
        return binding.root
    }


    private fun transferTo(fragment: Fragment){
        requireActivity().supportFragmentManager.commit {
            replace(R.id.nav_container, fragment)
        }

        (requireActivity() as ApplicationActivity).binding.bottomNavigator.visibility = View.GONE
    }


    private fun getDoctorDays(doctor: DoctorModel) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                var list: ArrayList<String>
                withContext(Dispatchers.IO) {
                    list = appointmentViewModel.getDoctorDays(doctor.doctorId) as ArrayList<String>
                }

                if(list.isNotEmpty()) {
                    val dateFormatterMin = SimpleDateFormat("yyyy-MM-dd").parse(list[0])
                    val dateFormatterMax = SimpleDateFormat("yyyy-MM-dd").parse(list.max())
                    binding.calendarView.apply {
                        calendar.time = dateFormatterMin
                        minDate = calendar.timeInMillis
                        calendar.time = dateFormatterMax
                        maxDate = calendar.timeInMillis
                    }
                }else{
                    binding.calendarView.maxDate = System.currentTimeMillis()
                    binding.calendarView.minDate = System.currentTimeMillis()
                    binding.nextBtn.isEnabled = false
                    Snackbar.make(view!!,"No Reservations Available", Snackbar.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                binding.calendarView.maxDate = System.currentTimeMillis()
                binding.calendarView.minDate = System.currentTimeMillis()
                binding.nextBtn.isEnabled = false
                Snackbar.make(view!!,"No Reservations Available", Snackbar.LENGTH_SHORT).show()
            }
        }
    }


}