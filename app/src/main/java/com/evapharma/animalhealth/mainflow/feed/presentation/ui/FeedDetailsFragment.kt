package com.evapharma.animalhealth.mainflow.feed.presentation.ui

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.databinding.FragmentFeedDetailsBinding
import com.evapharma.animalhealth.mainflow.booking.presentation.ui.SelectDoctorFragment
import com.evapharma.animalhealth.mainflow.feed.domain.model.Article
import com.evapharma.animalhealth.mainflow.feed.domain.model.Feed
import com.evapharma.animalhealth.mainflow.feed.presentation.adapters.ReferencesAdapter
import com.evapharma.animalhealth.mainflow.utils.DateConverter
import com.evapharma.animalhealth.utils.ImageLoader
import java.util.*

class FeedDetailsFragment : Fragment() {

    lateinit var binding: FragmentFeedDetailsBinding
    lateinit var adaptor: ReferencesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFeedDetailsBinding.inflate(layoutInflater)

        val post = arguments?.getParcelable<Article>("arc")


        binding.title.text = post?.heading ?: "Title"
        binding.date.text = DateConverter.covertTimeToText(post?.publishDate)

        binding.articleBody.text = post?.body ?: "Body"

        ImageLoader.loadImageIntoImageView(post?.image?:"" , binding.image)

        binding.backBtn.setOnClickListener {
            requireActivity().supportFragmentManager.commit {
                setCustomAnimations(
                    R.anim.slide_down,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_up
                )
                requireActivity().supportFragmentManager.popBackStack()
                replace(R.id.nav_container, FeedsFragment())
            }
        }

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().supportFragmentManager.commit {
                        setCustomAnimations(
                            R.anim.slide_down,
                            R.anim.fade_out,
                            R.anim.fade_in,
                            R.anim.slide_up
                        )
                        requireActivity().supportFragmentManager.popBackStack()
                        replace(R.id.nav_container, FeedsFragment())
                    }
                }
            })


       val  cardView = binding.baseCardview
        val arrow = binding.show
        val hiddenGroup = binding.cardGroup

        adaptor = post?.let { ReferencesAdapter(it.reference) }!!

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