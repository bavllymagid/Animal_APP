package com.evapharma.animalhealth.mainflow.booking.presentation.ui

import android.icu.util.Calendar
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.mainflow.booking.presentation.adapters.TimeAdaptor
import com.evapharma.animalhealth.mainflow.ApplicationActivity
import com.evapharma.animalhealth.databinding.FragmentBookAppointementBinding
import com.evapharma.animalhealth.mainflow.booking.domain.model.DoctorModel
import com.evapharma.animalhealth.mainflow.feed.presentation.ui.FeedsFragment
import java.text.DateFormat
import kotlin.collections.ArrayList


class BookAppointmentFragment : Fragment() , TimeAdaptor.OnItemSelected{

    lateinit var binding: FragmentBookAppointementBinding
    lateinit var adapter: TimeAdaptor
    var cnt = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookAppointementBinding.inflate(layoutInflater)
        binding.timeRc.visibility = View.GONE
        val doctor = arguments?.getParcelable<DoctorModel>("doctor")

        val items = listOf("10:30 AM", "11:00 PM", "12:00 AM", "1:00 PM", "5:00 AM")

        adapter = TimeAdaptor(ArrayList(items) , this)

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
            calendar.set(Calendar.DATE,4)
            minDate = System.currentTimeMillis()
            maxDate = calendar.timeInMillis
        }

        binding.calendarView.setOnDateChangeListener{
                _, year, month, dayOfMonth ->
            // set the calendar date as calendar view selected date
            calendar.set(year,month,dayOfMonth)

            // set this date as calendar view selected date
            binding.calendarView.date = calendar.timeInMillis

            // format the calendar view selected date
            val dateFormatter = DateFormat.getDateInstance(DateFormat.MEDIUM)
            Toast.makeText(context, dateFormatter.format(calendar.time), Toast.LENGTH_SHORT).show()
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

    override fun onTimeSlotSelected(position: Int) {

    }

    private fun transferTo(fragment: Fragment){
        requireActivity().supportFragmentManager.commit {
            replace(R.id.nav_container, fragment)
        }

        (requireActivity() as ApplicationActivity).binding.bottomNavigator.visibility = View.GONE
    }



}