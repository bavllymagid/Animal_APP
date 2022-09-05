package com.evapharma.animalhealth.mainflow.feed.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.mainflow.feed.presentation.adapters.FeedAdapter
import com.evapharma.animalhealth.mainflow.ApplicationActivity
import com.evapharma.animalhealth.databinding.FragmentFeedsBinding
import com.evapharma.animalhealth.mainflow.booking.presentation.ui.ui.SelectDoctorFragment
import com.evapharma.animalhealth.mainflow.feed.domain.model.PostsRequest
import com.evapharma.animalhealth.mainflow.feed.presentation.viewmodel.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedsFragment : Fragment() {


    private lateinit var binding: FragmentFeedsBinding
    lateinit var adapter: FeedAdapter
    lateinit var feedViewModel: FeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as ApplicationActivity).binding.bottomNavigator.visibility = View.VISIBLE
        adapter = FeedAdapter()
        feedViewModel = ViewModelProvider(this)[FeedViewModel::class.java]

        feedViewModel.getPosts(PostsRequest(1,"")).observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        binding = FragmentFeedsBinding.inflate(layoutInflater)
        binding.feedList.adapter = adapter

        binding.bookBtn.setOnClickListener{
            transferTo(SelectDoctorFragment())
            (requireActivity() as ApplicationActivity).binding.bottomNavigator.visibility = View.GONE
        }

        binding.searchView.setOnClickListener {
            transferTo(FeedSearchFragment())
        }

        return binding.root
    }

    private fun transferTo(fragment: Fragment){
        requireActivity().supportFragmentManager.commit {
            addToBackStack(this.toString())
            replace(R.id.nav_container, fragment)
        }

        (requireActivity() as ApplicationActivity).binding.bottomNavigator.visibility = View.GONE
    }

}