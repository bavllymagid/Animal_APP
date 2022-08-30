package com.evapharma.animalhealth.applicationflow.presentation.ui.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.applicationflow.domain.model.FeedModel
import com.evapharma.animalhealth.applicationflow.presentation.adapters.FeedAdapter
import com.evapharma.animalhealth.applicationflow.presentation.ui.ApplicationActivity
import com.evapharma.animalhealth.databinding.FragmentFeedsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedsFragment : Fragment() {


    private lateinit var binding: FragmentFeedsBinding
    lateinit var adapter: FeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as ApplicationActivity).binding.bottomNavigator.visibility = View.VISIBLE
        adapter = FeedAdapter()
        val list = listOf(FeedModel("dg","Medicine Article",BitmapFactory.decodeResource(resources, R.drawable.book_doctor_img),"1h"),
            FeedModel("d2g","Medical Post",BitmapFactory.decodeResource(resources, R.drawable.book_doctor_img),"2d"),
            FeedModel("dg3","Medical Post",BitmapFactory.decodeResource(resources, R.drawable.book_doctor_img),"2m"))

        adapter.submitList(list)

        binding = FragmentFeedsBinding.inflate(layoutInflater)
        binding.feedList.adapter = adapter

        binding.bookBtn.setOnClickListener{
            transferTo()
        }

        return binding.root
    }

    private fun transferTo(){
        val selectDoctorFragment = SelectDoctorFragment()
        requireActivity().supportFragmentManager.commit {
            addToBackStack(this.toString())
            replace(R.id.nav_container, selectDoctorFragment)
        }

        (requireActivity() as ApplicationActivity).binding.bottomNavigator.visibility = View.GONE
    }


}