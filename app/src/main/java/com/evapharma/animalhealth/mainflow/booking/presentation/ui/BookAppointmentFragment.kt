package com.evapharma.animalhealth.mainflow.booking.presentation.ui

import android.icu.util.Calendar
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.DatePicker
import android.widget.ListAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.mainflow.booking.presentation.adapters.TimeAdaptor
import com.evapharma.animalhealth.mainflow.ApplicationActivity
import com.evapharma.animalhealth.databinding.FragmentBookAppointementBinding
import com.evapharma.animalhealth.mainflow.booking.domain.model.DoctorModel
import com.evapharma.animalhealth.mainflow.booking.domain.usecases.BookAnAppointment
import com.evapharma.animalhealth.mainflow.booking.presentation.viewmodel.BookingViewModel
import com.evapharma.animalhealth.mainflow.feed.presentation.ui.FeedsFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import kotlin.collections.ArrayList

@AndroidEntryPoint
class BookAppointmentFragment : Fragment(){

    lateinit var binding: FragmentBookAppointementBinding
    lateinit var adapter: TimeAdaptor
    lateinit var appointmentViewModel: BookingViewModel
    var cnt = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookAppointementBinding.inflate(layoutInflater)
        appointmentViewModel = ViewModelProvider(this)[BookingViewModel::class.java]
        binding.timeRc.visibility = View.GONE
        val doctor = arguments?.getParcelable<DoctorModel>("doctor")

        val datePicker = binding.calendarView.getChildAt(0).
                resources.getIdentifier("date_picker_header", "id", "android")
        binding.calendarView.findViewById<View>(datePicker).visibility = View.GONE

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

        val calendar = Calendar.getInstance()
        binding.calendarView.apply {
            calendar.set(Calendar.DATE,30)
            minDate = System.currentTimeMillis()
            maxDate = calendar.timeInMillis
        }

        binding.calendarView.setOnDateChangedListener(object : DatePicker.OnDateChangedListener {
            override fun onDateChanged(
                view: DatePicker?,
                year: Int,
                monthOfYear: Int,
                dayOfMonth: Int
            ) {
                calendar.set(year,monthOfYear,dayOfMonth)

                val dateFormatter = DateFormat.getDateInstance(DateFormat.MEDIUM)
                Toast.makeText(context, dateFormatter.format(calendar.time), Toast.LENGTH_SHORT).show()
            }
        })

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



}