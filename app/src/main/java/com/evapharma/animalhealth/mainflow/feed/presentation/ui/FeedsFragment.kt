package com.evapharma.animalhealth.mainflow.feed.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.databinding.FragmentFeedsBinding
import com.evapharma.animalhealth.mainflow.ApplicationActivity
import com.evapharma.animalhealth.mainflow.booking.presentation.ui.ui.SelectDoctorFragment
import com.evapharma.animalhealth.mainflow.feed.domain.model.Feed
import com.evapharma.animalhealth.mainflow.feed.domain.model.PostsRequest
import com.evapharma.animalhealth.mainflow.feed.presentation.adapters.FeedAdapter
import com.evapharma.animalhealth.mainflow.feed.presentation.viewmodel.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FeedsFragment : Fragment(), FeedAdapter.OnItemSelected {


    private lateinit var binding: FragmentFeedsBinding
    lateinit var adapter: FeedAdapter
    lateinit var feedViewModel: FeedViewModel


    lateinit var post:PostsRequest

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as ApplicationActivity).binding.bottomNavigator.visibility = View.VISIBLE
        adapter = FeedAdapter(this)
        feedViewModel = ViewModelProvider(this)[FeedViewModel::class.java]


        post = PostsRequest(1,"")

        feedViewModel.getPosts(post).observe(viewLifecycleOwner, Observer {
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

        //Pagination
        binding.feedList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    post.page = post.page + 1
                    binding.progressBar.visibility = View.VISIBLE
                    feedViewModel.getPosts(post).observe(viewLifecycleOwner, Observer {
                        binding.progressBar.visibility = View.GONE
                        adapter.submitList(adapter.currentList + it)
                    })
                }
            }
        })


        return binding.root
    }

    private fun transferTo(fragment: Fragment, item:Feed? = null){
        val bundle = Bundle()
        bundle.putParcelable("arc", item)
        fragment.arguments = bundle
        requireActivity().supportFragmentManager.commit {
            addToBackStack(this.toString())
            replace(R.id.nav_container, fragment)
        }
    }

    override fun onItemClicked(feedObject: Feed) {
        transferTo(FeedDetailsFragment(), feedObject)
    }



}