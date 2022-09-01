package com.evapharma.animalhealth.applicationflow.presentation.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.applicationflow.domain.model.DoctorModel
import com.evapharma.animalhealth.applicationflow.presentation.adapters.DoctorListAdapter
import com.evapharma.animalhealth.applicationflow.presentation.ui.ApplicationActivity
import com.evapharma.animalhealth.databinding.FragmentSelectDoctorBinding
import com.google.gson.Gson


class SelectDoctorFragment : Fragment() , DoctorListAdapter.OnDoctorSelected{

    lateinit var binding: FragmentSelectDoctorBinding
    lateinit var adapter: DoctorListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectDoctorBinding.inflate(layoutInflater)

        adapter = DoctorListAdapter(this)

        val list = listOf(DoctorModel("1", "Dr.fahmy mohamed", null, "tomorrow"),
            DoctorModel("2", "Dr.fahmy mohamed", null, "tomorrow"),
            DoctorModel("3", "Dr.fahmy mohamed", null, "tomorrow"),
            DoctorModel("4", "Dr.fahmy mohamed", null, "tomorrow"))

        adapter.submitList(list)
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