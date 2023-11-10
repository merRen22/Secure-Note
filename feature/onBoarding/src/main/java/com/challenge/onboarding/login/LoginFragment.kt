package com.challenge.onboarding.login

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
import com.challenge.get.onBoarding.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var navController: NavController

    private lateinit var binding: FragmentLoginBinding

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(
            inflater,
            container,
            false,
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()

        binding.also {
            it.lifecycleOwner = viewLifecycleOwner
        }

        if (loginViewModel.username.isNullOrEmpty()) {
            navigateToRegisterUser()
        }

        binding.composeContainer.setContent {
            ChallengeTheme() {
                LoginScreen(
                    username = loginViewModel.username.toString(),
                    onLoginClick = { username ->
                        //login(username)
                        loginViewModel.getFromService().observe(viewLifecycleOwner) {a->
                            println(a)
                        }
                    },
                    onBiometricAuth = { message ->
                        navigateUsingBiometric(message)
                    },
                    fragment = this,
                )
            }
        }
    }

    private fun login(password: String) {
        if (loginViewModel.login(password)) {
            Toast.makeText(requireContext(), "Welcome ${loginViewModel.username}", Toast.LENGTH_LONG).show()
            navigateToHome()
        } else {
            Toast.makeText(requireContext(), "Invalid credentials", Toast.LENGTH_LONG).show()
        }
    }

    private fun navigateUsingBiometric(message: String) {
        if (message.isEmpty()) {
            navigateToHome()
        } else {
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        }
    }

    private fun navigateToRegisterUser() {
        if (::navController.isInitialized) {
            navController.navigate(R.id.action_loginFragment_to_createUserFragment)
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
