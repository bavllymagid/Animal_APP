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
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.authflow.presentation.viewmodel.AuthViewModel
import com.evapharma.animalhealth.mainflow.booking.presentation.adapters.TimeAdaptor
import com.evapharma.animalhealth.mainflow.ApplicationActivity
import com.evapharma.animalhealth.databinding.FragmentBookAppointementBinding
import com.evapharma.animalhealth.mainflow.booking.domain.model.AppointmentModel
import com.evapharma.animalhealth.mainflow.booking.domain.model.DateTimeSlot
import com.evapharma.animalhealth.mainflow.booking.domain.model.DoctorModel
import com.evapharma.animalhealth.mainflow.booking.presentation.viewmodel.BookingViewModel
import com.evapharma.animalhealth.mainflow.feed.presentation.ui.FeedsFragment
import com.evapharma.animalhealth.mainflow.utils.DateConverter
import com.evapharma.animalhealth.utils.ImageLoader
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

    private lateinit var binding: FragmentBookAppointementBinding
    private lateinit var adapter: TimeAdaptor
    private lateinit var appointmentViewModel: BookingViewModel
    private lateinit var userViewModel: AuthViewModel
    private lateinit var calendar: Calendar


    private var cnt = 0
    private var currentDay = ""
    private var slotID = -1

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
                if (doctor != null) {
                    requestSent(doctor)
                }
            }
        }

        ImageLoader.loadImageIntoImageView(doctor?.image?:"", binding.profileImage)

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
            binding.timeTv.text = currentDay
        }

        binding.timeRc.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                binding.nextBtn.isEnabled = true
                val item = adapter.getTimeList()[position]
                slotID = item.slotId
            }



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
            specializationTv2.text = doctor?.specialization
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

    private fun requestSent(doctor: DoctorModel): Boolean {
        CoroutineScope(Dispatchers.Main).launch {
                var success = false
                withContext(Dispatchers.IO){
                    try {
                    success = appointmentViewModel.bookAppointment("Bearer ${userViewModel.getLocalToken()}", AppointmentModel(slotID,doctor.doctorId))
                    }catch (e:Exception){
                        print("MYAPP ${e.message.toString()}")
                    }
                }
                if(!success){
                    getDoctorDays(doctor)
                    binding.calenderCard.visibility = View.VISIBLE
                    binding.timeRc.visibility = View.GONE
                    binding.nextBtn.setImageResource(R.drawable.arrow)
                    binding.nextBtn.isEnabled = true
                    cnt = 0
                    Snackbar.make(view!!,"Something went wrong Please try again later", Snackbar.LENGTH_LONG).show()
                }else{
                    Snackbar.make(view!!,"Reservation Succeeded", Snackbar.LENGTH_LONG).show()
                    transferTo(FeedsFragment())
                }
        }
        return true
    }


    private fun transferTo(fragment: Fragment) {
        requireActivity().supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_down,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_up
            )
            replace(R.id.nav_container, fragment)
        }

        (requireActivity() as ApplicationActivity).binding.bottomNavigator.visibility = View.GONE
    }


    private fun getDoctorDays(doctor: DoctorModel) {
        CoroutineScope(Dispatchers.Main).launch {

                var list = ArrayList<String>()
                withContext(Dispatchers.IO) {
                    try {
                        list =
                            DateConverter
                                .listToStringDate(appointmentViewModel.getDoctorDays(doctor.doctorId) as ArrayList<String>)
                    } catch (e: Exception) {
                       print("MyApp" + e.message.toString())
                    }
                }
                if (list.isNotEmpty()) {
                    currentDay = list[0]
                    val dateFormatterMin = SimpleDateFormat("yyyy-MM-dd").parse(list[0])
                    val dateFormatterMax = SimpleDateFormat("yyyy-MM-dd").parse(list.max())
                    calendar.time = dateFormatterMax
                    binding.calendarView.maxDate = calendar.timeInMillis
                    calendar.time = dateFormatterMin
                    binding.calendarView.minDate = calendar.timeInMillis
                    getDoctorTimes(doctor)
                } else {
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
                    Snackbar.make(view!!, "Day $currentDay No Reservations Available", Snackbar.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                binding.nextBtn.isEnabled = false
                Snackbar.make(view!!, "No Reservations Available", Snackbar.LENGTH_SHORT).show()
            }
        }
    }


}