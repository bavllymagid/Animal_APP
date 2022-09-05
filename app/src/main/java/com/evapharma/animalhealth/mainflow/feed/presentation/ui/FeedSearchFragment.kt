package com.evapharma.animalhealth.mainflow.feed.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.databinding.FragmentFeedSearchBinding
import com.evapharma.animalhealth.mainflow.ApplicationActivity


class FeedSearchFragment : Fragment() {

    lateinit var binding:FragmentFeedSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedSearchBinding.inflate(layoutInflater)
        (requireActivity() as ApplicationActivity).binding.bottomNavigator.visibility = View.GONE

        binding.backBtn.setOnClickListener{
            transferTo(FeedsFragment())
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