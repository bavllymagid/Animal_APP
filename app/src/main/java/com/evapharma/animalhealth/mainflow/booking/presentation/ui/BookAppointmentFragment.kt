package com.evapharma.animalhealth.mainflow.booking.presentation.ui

import android.icu.util.Calendar
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.CalendarView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.authflow.presentation.viewmodel.AuthViewModel
import com.evapharma.animalhealth.mainflow.booking.presentation.adapters.TimeAdaptor
import com.evapharma.animalhealth.mainflow.ApplicationActivity
import com.evapharma.animalhealth.databinding.FragmentBookAppointementBinding
import com.evapharma.animalhealth.mainflow.booking.domain.model.DateTimeSlot
import com.evapharma.animalhealth.mainflow.booking.domain.model.DoctorModel
import com.evapharma.animalhealth.mainflow.booking.presentation.viewmodel.BookingViewModel
import com.evapharma.animalhealth.mainflow.feed.presentation.ui.FeedsFragment
import com.evapharma.animalhealth.mainflow.utils.DateConverter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

@AndroidEntryPoint
class BookAppointmentFragment : Fragment() {

    lateinit var binding: FragmentBookAppointementBinding
    lateinit var adapter: TimeAdaptor
    lateinit var appointmentViewModel: BookingViewModel
    lateinit var userViewModel: AuthViewModel
    private lateinit var calendar: Calendar


    var cnt = 0
    var currentDay = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookAppointementBinding.inflate(layoutInflater)
        appointmentViewModel = ViewModelProvider(this)[BookingViewModel::class.java]
        userViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        binding.timeRc.visibility = View.GONE
        val doctor = arguments?.getParcelable<DoctorModel>("doctor")


        adapter = TimeAdaptor(ArrayList(), context?.applicationContext!!)
        binding.nextBtn.setOnClickListener {
            if (cnt == 0) {
                binding.calenderCard.visibility = View.INVISIBLE
                binding.timeRc.visibility = View.VISIBLE
                binding.nextBtn.setImageResource(R.drawable.right)
                binding.nextBtn.isEnabled = false
                cnt++
            } else {
                transferTo(FeedsFragment())
            }
        }

        calendar = Calendar.getInstance()
        if (doctor != null) {
            getDoctorDays(doctor)
        }

        binding.timeRc.adapter = adapter

        binding.calendarView.setOnDateChangeListener { _: CalendarView, year: Int, month: Int, dayOfMonth: Int ->
            currentDay = "$year-${month + 1}-$dayOfMonth"
            if (doctor != null) {
                getDoctorTimes(doctor)
            }
        }

        binding.timeRc.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, _, _ -> binding.nextBtn.isEnabled = true }


        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (cnt > 0) {
                        binding.calenderCard.visibility = View.VISIBLE
                        binding.timeRc.visibility = View.GONE
                        binding.nextBtn.setImageResource(R.drawable.arrow)
                        binding.nextBtn.isEnabled = true
                        cnt = 0
                    } else {
                        transferTo(SelectDoctorFragment())
                    }
                }
            })

        binding.apply {
            doctorNameTv.text = doctor?.userName
            timeTv.text = DateConverter.stringToDate(doctor?.nearestSlot?.startAt ?: "")
        }

        binding.customToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.exit -> {
                    transferTo(SelectDoctorFragment())
                }
            }
            return@setOnMenuItemClickListener false
        }
        return binding.root
    }


    private fun transferTo(fragment: Fragment) {
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
                    list =
                        DateConverter
                            .listToStringDate(appointmentViewModel.getDoctorDays(doctor.doctorId) as ArrayList<String>)
                }
                if (list.isNotEmpty()) {
                    currentDay = list.max()
                    val dateFormatterMin = SimpleDateFormat("yyyy-MM-dd").parse(list[0])
                    val dateFormatterMax = SimpleDateFormat("yyyy-MM-dd").parse(list.max())
                    binding.calendarView.apply {
                        calendar.time = dateFormatterMin
                        minDate = calendar.timeInMillis
                        calendar.time = dateFormatterMax
                        maxDate = calendar.timeInMillis
                    }
                } else {
                    binding.calendarView.maxDate = System.currentTimeMillis()
                    binding.calendarView.minDate = System.currentTimeMillis()
                    binding.nextBtn.isEnabled = false
                    Snackbar.make(view!!, "No Reservations Available", Snackbar.LENGTH_SHORT).show()
                }
                getDoctorTimes(doctor)
            } catch (e: Exception) {
                binding.calendarView.maxDate = System.currentTimeMillis()
                binding.calendarView.minDate = System.currentTimeMillis()
                binding.nextBtn.isEnabled = false
                Snackbar.make(view!!, "No Reservations Available", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun getDoctorTimes(doctor: DoctorModel) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                var list: ArrayList<DateTimeSlot>
                withContext(Dispatchers.IO) {
                    list = appointmentViewModel.getDoctorScheduleForADay(
                        doctor.doctorId,
                        currentDay
                    ) as ArrayList<DateTimeSlot>
                }
                if (list.isNotEmpty()) {
                    adapter.setTimeList(list)
                    binding.nextBtn.isEnabled = true
                } else {
                    binding.nextBtn.isEnabled = false
                }
            } catch (e: Exception) {
                binding.nextBtn.isEnabled = false
                Snackbar.make(view!!, "No Reservations Available", Snackbar.LENGTH_SHORT).show()
            }
        }
    }


}