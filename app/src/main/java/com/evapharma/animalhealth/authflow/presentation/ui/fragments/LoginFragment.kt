package com.evapharma.animalhealth.authflow.presentation.ui.fragments
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.databinding.FragmentLoginBinding
import com.evapharma.animalhealth.mainflow.ApplicationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    var AuthToken = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //inflating login fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        binding.PhoneInupte
        //Going From The login Screen To the Home Screen Without account
        binding.SkipBtn.setOnClickListener()
        {
            val intent = Intent(this.requireContext(), ApplicationActivity::class.java)
            startActivity(intent)
        }
        //Going From The login Screen To Register screen
        binding.RegisterBtn.setOnClickListener()
        {
            Navigation.findNavController(view!!).navigate(R.id.registerFragment);
        }
        binding.SignInBTN.setOnClickListener()
        {
            if (AuthToken == ""|| AuthToken == null)
            {
                binding.messageTv.text="Error in Phone number or a password"
            }
            else
            {
                val intent = Intent(this.requireContext(), ApplicationActivity::class.java)
                startActivity(intent)
            }

        }
        return binding.root
    }


}

