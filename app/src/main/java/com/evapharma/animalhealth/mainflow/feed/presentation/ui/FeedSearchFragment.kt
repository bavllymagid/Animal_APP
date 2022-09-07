package com.evapharma.animalhealth.mainflow.feed.presentation.ui

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.databinding.FragmentFeedSearchBinding
import com.evapharma.animalhealth.mainflow.ApplicationActivity
import com.evapharma.animalhealth.mainflow.feed.domain.model.Feed
import com.evapharma.animalhealth.mainflow.feed.domain.model.FeedX
import com.evapharma.animalhealth.mainflow.feed.domain.model.PostsRequest
import com.evapharma.animalhealth.mainflow.feed.presentation.adapters.FeedAdapter
import com.evapharma.animalhealth.mainflow.feed.presentation.viewmodel.FeedViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class FeedSearchFragment : Fragment(), FeedAdapter.OnItemSelected {

    lateinit var binding: FragmentFeedSearchBinding
    lateinit var adapter: FeedAdapter
    lateinit var feedViewModel: FeedViewModel
    lateinit var post: PostsRequest

    var IS_INTERNET_FOUND = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedSearchBinding.inflate(layoutInflater)
        feedViewModel = ViewModelProvider(this)[FeedViewModel::class.java]

        post = PostsRequest(1, "")

        (requireActivity() as ApplicationActivity).binding.bottomNavigator.visibility = View.GONE
        adapter = FeedAdapter(this)
        IS_INTERNET_FOUND = false

        binding.backBtn.setOnClickListener {
            transferTo(FeedsFragment())
        }


        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchData.visibility = View.INVISIBLE
                post.keyword = binding.searchBar.query.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    val list = getResponse()
                    withContext(Dispatchers.Main) {
                        binding.searchBar.clearFocus()
                        adapter.submitList(list)
                        if(list.isEmpty()){
                            binding.searchData.text = "No Data To Be Displayed"
                            binding.searchData.visibility = View.VISIBLE
                        }
                    }
                }
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
                if (!binding.feedList.canScrollVertically(1)) {
                    post.page = post.page + 1
                    binding.progressBar.visibility = View.VISIBLE
                    if (post.page <= post.maxPage) {
                        CoroutineScope(Dispatchers.IO).launch {
                            val list = paginate()
                            withContext(Dispatchers.Main) {
                                if(list.isNotEmpty()){
                                    adapter.submitList(list)
                                }
                                binding.progressBar.visibility = View.GONE
                            }
                        }
                    } else {
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
            replace(R.id.nav_container, fragment)
        }
    }

    override fun onItemClicked(feedObject: Feed) {
        transferTo(FeedDetailsFragment(), feedObject)
    }


    suspend fun paginate(): ArrayList<Feed> {
        val list = ArrayList<Feed>()
        try {
            val response = feedViewModel.getSearchResult(post)
            list.addAll(adapter.currentList)
            if (response != null) {
                list.addAll(response.articlesPosts)
                post.page = response.currentPage
                post.maxPage = response.totalPages
            }
        }catch (e: Exception) {
            println(e.message.toString())
        }
        return list
    }

    suspend fun getResponse(): ArrayList<Feed>{
        var list = ArrayList<Feed>()
        try {
            val response = feedViewModel.getSearchResult(post)
            if (response != null) {
                list = response.articlesPosts as ArrayList<Feed>
                post.page = response.currentPage
                post.maxPage = response.totalPages
            }
        }catch (e: Exception) {
            println(e.message.toString())
        }
        return list
    }

}