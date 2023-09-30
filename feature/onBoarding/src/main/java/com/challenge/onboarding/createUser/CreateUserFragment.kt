package com.challenge.onboarding.createUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import com.challenge.get.base.AppConstants
import com.challenge.get.base.compose.ChallengeTheme
import com.challenge.get.onBoarding.R
import com.challenge.get.onBoarding.databinding.FragmentCreateUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateUserFragment : Fragment(R.layout.fragment_create_user) {

    private lateinit var navController: NavController

    lateinit var binding: FragmentCreateUserBinding

    private val viewModel: CreateUserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCreateUserBinding.inflate(
            inflater,
            container,
            false,
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()

        binding.composeContainer.setContent {
            ChallengeTheme() {
                CreateUserScreen(
                    onRegisterClick = { username, password ->
                        createUser(username, password)
                    },
                )
            }
        }
    }

    private fun createUser(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_LONG).show()
            return
        } else {
            if (viewModel.createUser(username, password)) {
                navigateToHome()
            } else {
                Toast.makeText(requireContext(), "Error creating user", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun navigateToHome() {
        val request = NavDeepLinkRequest.Builder
            .fromUri(AppConstants.HOME_DEEP_LINK.toUri())
            .build()

        if (::navController.isInitialized) {
            navController.popBackStack()
            navController.navigate(request)
        }
    }
}
