package com.evapharma.animalhealth.mainflow.booking.presentation.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.mainflow.booking.presentation.adapters.DoctorListAdapter
import com.evapharma.animalhealth.mainflow.ApplicationActivity
import com.evapharma.animalhealth.databinding.FragmentSelectDoctorBinding
import com.evapharma.animalhealth.mainflow.booking.domain.model.DoctorModel
import com.evapharma.animalhealth.mainflow.booking.presentation.viewmodel.BookingViewModel
import com.evapharma.animalhealth.mainflow.booking.utils.StringToDateAndTime
import com.evapharma.animalhealth.mainflow.feed.presentation.ui.FeedsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectDoctorFragment : Fragment() , DoctorListAdapter.OnDoctorSelected{

    lateinit var binding: FragmentSelectDoctorBinding
    private lateinit var adapter: DoctorListAdapter
    lateinit var doctorViewModel:BookingViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectDoctorBinding.inflate(layoutInflater)
        (requireActivity() as ApplicationActivity).binding.bottomNavigator.visibility = View.GONE
        doctorViewModel = ViewModelProvider(this)[BookingViewModel::class.java]

        adapter = DoctorListAdapter(this)
        println("Date ${StringToDateAndTime.stringToDate("2012-04-23T18:25:43.511Z").toString()}")
        doctorViewModel.getDoctorsList(1).observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        //pagination remaining
        binding.doctorsList.adapter = adapter

        binding.backBtn.setOnClickListener{
            activity?.supportFragmentManager?.backStackEntryCount?.let { it1 ->
                repeat(it1) {
                    activity?.supportFragmentManager?.popBackStack()
                }
            }
            transferTo(FeedsFragment())
        }

        return binding.root
    }

    override fun onBookAppointmentClicked(doctor: DoctorModel) {
        val bookAppointmentFragment = BookAppointmentFragment()
        val bundle = Bundle()
        bundle.putParcelable("doctor", doctor)
        bookAppointmentFragment.arguments = bundle
        requireActivity().supportFragmentManager.commit {
            addToBackStack(this.toString())
            replace(R.id.nav_container, bookAppointmentFragment)
        }
    }

    private fun transferTo(fragment: Fragment){
        requireActivity().supportFragmentManager.commit {
            replace(R.id.nav_container, fragment)
        }

        (requireActivity() as ApplicationActivity).binding.bottomNavigator.visibility = View.GONE
    }




}