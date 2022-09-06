package com.evapharma.animalhealth.mainflow.feed.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.databinding.FragmentFeedSearchBinding
import com.evapharma.animalhealth.mainflow.ApplicationActivity
import com.evapharma.animalhealth.mainflow.feed.domain.model.Feed
import com.evapharma.animalhealth.mainflow.feed.domain.model.PostsRequest
import com.evapharma.animalhealth.mainflow.feed.presentation.adapters.FeedAdapter
import com.evapharma.animalhealth.mainflow.feed.presentation.viewmodel.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FeedSearchFragment : Fragment(), FeedAdapter.OnItemSelected {

    lateinit var binding:FragmentFeedSearchBinding
    lateinit var adapter:FeedAdapter
    lateinit var feedViewModel: FeedViewModel
    lateinit var post: PostsRequest


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedSearchBinding.inflate(layoutInflater)
        feedViewModel = ViewModelProvider(this)[FeedViewModel::class.java]

        post = PostsRequest(1, "")

        (requireActivity() as ApplicationActivity).binding.bottomNavigator.visibility = View.GONE
        adapter = FeedAdapter(this)


        binding.backBtn.setOnClickListener{
            transferTo(FeedsFragment())
        }


        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.scrollScreen.visibility = View.VISIBLE
                binding.searchData.visibility = View.GONE
                post.keyword = binding.searchBar.query.toString()
                feedViewModel.getSearchResult(post).observe(viewLifecycleOwner) {
                    if (it != null) {
                        adapter.submitList(it.articlesPosts)
                        post.page = it.currentPage
                        post.maxPage = it.totalPages
                    }else{
                        Toast.makeText(context, "No Data To Be Displayed", Toast.LENGTH_SHORT).show()
                    }
                }
                binding.searchBar.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


        binding.feedList.adapter = adapter

        //Pagination
        binding.feedList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                binding.progressBar.visibility = View.VISIBLE
                if (!binding.scrollScreen.canScrollVertically(1) && !binding.feedList.canScrollVertically(1)) {
                    post.page = post.page + 1
                    if (post.page <= post.maxPage) {
                        feedViewModel.getSearchResult(post).observe(viewLifecycleOwner) {
                            val list = ArrayList<Feed>()
                            list.addAll(adapter.currentList)
                            if (it != null) {
                                list.addAll(it.articlesPosts)
                            }
                            adapter.submitList(list)
                            if (it != null) {
                                post.page = it.currentPage
                            }
                            binding.progressBar.visibility = View.GONE
                        }
                    }else{
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        })

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