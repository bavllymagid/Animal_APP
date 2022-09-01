package com.evapharma.animalhealth.applicationflow.presentation.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Adapter
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.applicationflow.domain.model.DoctorModel
import com.evapharma.animalhealth.applicationflow.presentation.adapters.TimeAdaptor
import com.evapharma.animalhealth.applicationflow.presentation.ui.ApplicationActivity
import com.evapharma.animalhealth.databinding.FragmentBookAppointementBinding
import java.text.FieldPosition
import java.util.*
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

        binding.nextButton.setOnClickListener{
            if(cnt == 0) {
                binding.calenderCard.visibility = View.INVISIBLE
                binding.timeRc.visibility = View.VISIBLE
                cnt++
            }else{
                transferTo(FeedsFragment())
            }
        }
        binding.calendarView.apply {
            minDate = System.currentTimeMillis()
        }

        binding.timeRc.adapter = adapter

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                binding.calenderCard.visibility = View.VISIBLE
                binding.timeRc.visibility = View.GONE
                cnt = 0
            }
        })

        binding.apply {
            doctorNameTv.text = doctor?.name
        }

        binding.customToolbar.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.exit -> transferTo(SelectDoctorFragment())
            }
            return@setOnMenuItemClickListener false
        }
        return binding.root
    }

    override fun onTimeSlotSelected(position: Int) {

    }

    private fun transferTo(fragment: Fragment){
        requireActivity().supportFragmentManager.commit {
            addToBackStack(this.toString())
            replace(R.id.nav_container, fragment)
        }

        (requireActivity() as ApplicationActivity).binding.bottomNavigator.visibility = View.GONE
    }



}