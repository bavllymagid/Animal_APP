package com.evapharma.animalhealth.mainflow.feed.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.databinding.FragmentFeedDetailsBinding
import com.evapharma.animalhealth.databinding.FragmentFeedSearchBinding

class FeedDetailsFragment : Fragment() {

    lateinit var binding: FragmentFeedDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFeedDetailsBinding.inflate(layoutInflater)

        return binding.root
    }


}