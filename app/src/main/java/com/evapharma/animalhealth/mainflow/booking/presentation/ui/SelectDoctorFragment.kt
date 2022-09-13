package com.evapharma.animalhealth.mainflow.booking.presentation.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.authflow.presentation.ui.AuthActivity
import com.evapharma.animalhealth.mainflow.booking.presentation.adapters.DoctorListAdapter
import com.evapharma.animalhealth.mainflow.ApplicationActivity
import com.evapharma.animalhealth.databinding.FragmentSelectDoctorBinding
import com.evapharma.animalhealth.mainflow.booking.domain.model.DoctorModel
import com.evapharma.animalhealth.mainflow.booking.domain.model.DoctorModelX
import com.evapharma.animalhealth.mainflow.booking.presentation.viewmodel.BookingViewModel
import com.evapharma.animalhealth.mainflow.feed.domain.model.PostsRequest
import com.evapharma.animalhealth.mainflow.feed.presentation.ui.FeedsFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class SelectDoctorFragment : Fragment() , DoctorListAdapter.OnDoctorSelected{

    lateinit var binding: FragmentSelectDoctorBinding
    private lateinit var adapter: DoctorListAdapter
    private lateinit var doctorViewModel:BookingViewModel
    lateinit var postsRequest: PostsRequest
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectDoctorBinding.inflate(layoutInflater)
        (requireActivity() as ApplicationActivity).binding.bottomNavigator.visibility = View.GONE
        doctorViewModel = ViewModelProvider(this)[BookingViewModel::class.java]
        postsRequest = PostsRequest(1,"")

        adapter = DoctorListAdapter(this)

        getDoctors(false,"")

        binding.doctorsList.adapter = adapter

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                postsRequest.page = 1
                postsRequest.keyword = binding.searchBar.query.toString()
                getDoctors(false,postsRequest.keyword)
                binding.searchBar.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(binding.searchBar.query.toString().isEmpty()){
                    postsRequest.keyword = ""
                    getDoctors(false,postsRequest.keyword)
                }
                return true
            }
        })

        //pagination
        binding.doctorsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!binding.doctorsList.canScrollVertically(1)){
                    if(postsRequest.page < postsRequest.maxPage){
                        binding.docProgress.visibility = View.VISIBLE
                        postsRequest.page += 1
                        getDoctors(true,postsRequest.keyword)
                    }else{
                        binding.docProgress.visibility = View.GONE
                    }
                }
            }
        })

        binding.backBtn.setOnClickListener{
            activity?.supportFragmentManager?.backStackEntryCount?.let { it1 ->
                repeat(it1) {
                    activity?.supportFragmentManager?.popBackStack()
                }
            }
            transferTo(FeedsFragment())
        }

        return binding.root
    }

    override fun onBookAppointmentClicked(doctor: DoctorModel) {
        if(sharedPreferences.contains("User")) {
            if(doctor.nearestSlot != null) {
                val bookAppointmentFragment = BookAppointmentFragment()
                val bundle = Bundle()
                bundle.putParcelable("doctor", doctor)
                bookAppointmentFragment.arguments = bundle
                requireActivity().supportFragmentManager.commit {
                    setCustomAnimations(
                        R.anim.slide_down,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_up
                    )
                    addToBackStack(this.toString())
                    replace(R.id.nav_container, bookAppointmentFragment)
                }
            }else{
                Snackbar.make(view!!, "Dr. ${doctor.userName} Has No Reservations", Snackbar.LENGTH_SHORT).show()
            }
        }else{
            val intent = Intent(requireActivity() , AuthActivity::class.java)
            startActivity(intent)
        }
    }

    private fun transferTo(fragment: Fragment){
        requireActivity().supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_down,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_up
            )
            replace(R.id.nav_container, fragment)
        }

        (requireActivity() as ApplicationActivity).binding.bottomNavigator.visibility = View.GONE
    }


    private fun getDoctors(pagFlag:Boolean, keyword: String){
        CoroutineScope(Dispatchers.IO).launch {
            var doctor : DoctorModelX? = null
            try{
                doctor = doctorViewModel.getDoctorsList(keyword,postsRequest.page)
            }catch (e:Exception){
                e.message.toString()
            }
            withContext(Dispatchers.Main){
                if(doctor != null){
                    if(!pagFlag){
                        adapter.submitList(doctor.doctors)
                    }else{
                        adapter.submitList(adapter.currentList + doctor.doctors)
                    }
                    postsRequest.page = doctor.pageNumber
                    postsRequest.maxPage = doctor.maxPage
                    binding.docProgress.visibility = View.GONE
                    binding.docProgressInit.visibility = View.GONE
                    binding.mainView.visibility = View.VISIBLE
                    binding.noInternet.noInternet.visibility = View.GONE
                }else{
                    binding.mainView.visibility = View.GONE
                    binding.noInternet.noInternet.visibility = View.VISIBLE
                    binding.noInternet.swipeTv.text = "Try Again Later"
                }
            }
        }
    }


}