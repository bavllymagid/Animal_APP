
package com.evapharma.animalhealth.mainflow

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.authflow.presentation.ui.AuthActivity
import com.evapharma.animalhealth.databinding.ActivityApplicationBinding

import com.evapharma.animalhealth.mainflow.booking.presentation.ui.BookingsFragment
import com.evapharma.animalhealth.mainflow.feed.presentation.ui.FeedsFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ApplicationActivity : AppCompatActivity() {

    lateinit var binding: ActivityApplicationBinding

    @Inject
    lateinit var sharedPreferences: SharedPreferences
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
                    R.id.booking_nav -> {
                        if(sharedPreferences.contains("User")){
                            setCurrentFragment(bookingsFragment)
                        }else{
                            val intent = Intent(context , AuthActivity::class.java)
                            startActivity(intent)
                        }
                    }
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