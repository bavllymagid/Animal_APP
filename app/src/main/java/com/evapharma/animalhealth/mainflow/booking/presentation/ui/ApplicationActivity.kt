package com.evapharma.animalhealth.mainflow.booking.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.mainflow.booking.presentation.ui.fragments.BookingsFragment
import com.evapharma.animalhealth.mainflow.feed.presentation.ui.FeedsFragment
import com.evapharma.animalhealth.databinding.ActivityApplicationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApplicationActivity : AppCompatActivity() {

    lateinit var binding: ActivityApplicationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApplicationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val feedFragment = FeedsFragment()
        val bookingsFragment = BookingsFragment()

        binding.bottomNavigator.apply {
            setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.home_nav -> setCurrentFragment(feedFragment)
                    R.id.booking_nav -> setCurrentFragment(bookingsFragment)
                }
                true
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_container,fragment)
            commit()
        }
}