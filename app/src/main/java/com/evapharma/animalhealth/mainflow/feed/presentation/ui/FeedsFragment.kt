package com.evapharma.animalhealth.mainflow.feed.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
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
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FeedsFragment : Fragment(), FeedAdapter.OnItemSelected {


    private lateinit var binding: FragmentFeedsBinding
    lateinit var adapter: FeedAdapter
    lateinit var feedViewModel: FeedViewModel

    lateinit var post: PostsRequest

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as ApplicationActivity).binding.bottomNavigator.visibility = View.VISIBLE
        adapter = FeedAdapter(this)
        feedViewModel = ViewModelProvider(this)[FeedViewModel::class.java]

        post = PostsRequest(1, "",0)

        feedViewModel.getPosts(post).observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.submitList(it.articlesPosts)
                post.page = it.currentPage
                post.maxPage = it.totalPages
            } else {
                view?.let { it1 ->
                    Snackbar.make(
                        it1,
                        "Check Your Internet Connectivity",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    binding.searchView.isEnabled = false
                    binding.bookBtn.isEnabled = false
                }
            }
            binding.initBar.visibility = View.GONE
        }

        binding = FragmentFeedsBinding.inflate(layoutInflater)
        binding.feedList.adapter = adapter

        binding.bookBtn.setOnClickListener {
            transferTo(SelectDoctorFragment())
            (requireActivity() as ApplicationActivity).binding.bottomNavigator.visibility =
                View.GONE
        }

        binding.searchView.setOnClickListener {
            transferTo(FeedSearchFragment())
        }


        //Pagination
        binding.feedList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.feedList.canScrollVertically(1)) {
                    binding.progressBar.visibility = View.VISIBLE
                    post.page = post.page + 1
                    if (post.page <= post.maxPage) {
                        feedViewModel.getPosts(post).observe(viewLifecycleOwner) {
                            val list = ArrayList<Feed>()
                            list.addAll(adapter.currentList)
                            if (it != null) {
                                list.addAll(it.articlesPosts)
                                post.page = it.currentPage
                                adapter.submitList(list)
                            }else{
                                view?.let { it1 -> Snackbar.make(it1, "Check Your Internet Connectivity",Snackbar.LENGTH_SHORT).show() }
                            }
                            binding.progressBar.visibility = View.GONE
                        }
                    }else{
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
            })

        binding.refresh.setOnRefreshListener {
            post.page = 1
            feedViewModel.getPosts(post).observe(viewLifecycleOwner) {
                if (it != null) {
                    adapter.submitList(it.articlesPosts)
                    binding.searchView.isEnabled = true
                    binding.bookBtn.isEnabled = true
                }else{
                    view?.let { it1 -> Snackbar.make(it1, "Check Your Internet Connectivity",Snackbar.LENGTH_SHORT).show() }
                }
                binding.refresh.isRefreshing = false
            }
        }


        return binding.root
    }

    private fun transferTo(fragment: Fragment, item: Feed? = null) {
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