package com.evapharma.animalhealth.mainflow.feed.presentation.ui

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.databinding.FragmentFeedDetailsBinding
import com.evapharma.animalhealth.mainflow.booking.presentation.adapters.TimeAdaptor
import com.evapharma.animalhealth.mainflow.feed.domain.model.Feed
import com.evapharma.animalhealth.mainflow.feed.presentation.adapters.ReferencesAdapter
import com.evapharma.animalhealth.mainflow.feed.utils.DateConverter

class FeedDetailsFragment : Fragment() {

    lateinit var binding: FragmentFeedDetailsBinding
    lateinit var adaptor: ReferencesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFeedDetailsBinding.inflate(layoutInflater)

        val post = arguments?.getParcelable<Feed>("arc")


        binding.title.text = post?.text
        binding.date.text = DateConverter.covertTimeToText(post?.publishDate)

        binding.articleBody.text = post?.category

        binding.backBtn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }


       val  cardView = binding.baseCardview
        val arrow = binding.show
        val hiddenGroup = binding.cardGroup

        val items = mutableListOf<String>("500g", "1kg", "2kg")
        adaptor = ReferencesAdapter(items as ArrayList<String>)

        arrow.setOnClickListener {
            if (hiddenGroup.visibility == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardView, AutoTransition())
                hiddenGroup.visibility = View.GONE
                arrow.setImageResource(R.drawable.arrow_down)
            } else {
                TransitionManager.beginDelayedTransition(cardView, AutoTransition())
                hiddenGroup.visibility = View.VISIBLE
                arrow.setImageResource(R.drawable.arrow_up)
                binding.refList.adapter = adaptor
            }
        }

        return binding.root
    }


}