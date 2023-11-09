package com.challenge.get.settings

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import com.challenge.get.base.AppConstants
import com.challenge.get.base.compose.ChallengeTheme
import com.challenge.get.base.util.RequestState
import com.challenge.get.home.R
import com.challenge.get.home.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.InputStreamReader

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private lateinit var navController: NavController

    lateinit var binding: FragmentSettingsBinding

    private val settingsViewModel: SettingsViewModel by viewModels()

    private lateinit var getContent: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSettingsBinding.inflate(
            inflater,
            container,
            false,
        )
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    val reader = InputStreamReader(activity?.contentResolver?.openInputStream(uri))
                    readJsonFile(reader)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()

        (activity as AppCompatActivity).supportActionBar?.apply {
            this.show()
            this.title = "Settings"
            this.setDisplayHomeAsUpEnabled(true)
            this.setDisplayShowHomeEnabled(true)
        }

        binding.composeContainer.setContent {
            ChallengeTheme() {
                SettingsScreen(
                    username = settingsViewModel.username.toString(),
                    onDeleteAccountClick = {
                        deleteAccount()
                    },
                    onImportClick = {
                        showFilePicker()
                    },
                    onLogoutClick = {
                        navigateToLogin()
                    },
                    onDeleteNotesClicked = {
                        deleteNotes()
                    },
                )
            }
        }

        settingsViewModel.userDeleted.observe(viewLifecycleOwner) { userLogged ->
            if (userLogged) {
                navigateToLogin()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    private fun deleteAccount() {
        settingsViewModel.deleteAccount().observe(viewLifecycleOwner) { response ->
            when (response) {
                is RequestState.Loading -> {}
                is RequestState.Error -> {}
                is RequestState.Success -> {
                    Toast.makeText(requireContext(), response.value, Toast.LENGTH_LONG).show()
                    navigateToLogin()
                }
            }
        }
    }

    private fun deleteNotes() {
        settingsViewModel.deleteNotes().observe(viewLifecycleOwner) { response ->
            when (response) {
                is RequestState.Loading -> {}
                is RequestState.Error -> {
                    Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_LONG).show()
                }
                is RequestState.Success -> {
                    Toast.makeText(requireContext(), response.value, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun navigateToLogin() {
        val request = NavDeepLinkRequest.Builder
            .fromUri(AppConstants.LOGIN_DEEP_LINK.toUri())
            .build()

        if (::navController.isInitialized) {
            navController.currentBackStack.value.map {
                navController.popBackStack()
            }.also {
                navController.navigate(request)
            }
        }
    }

    private fun readJsonFile(reader: InputStreamReader) {
        settingsViewModel.readJSONFile(reader).observe(viewLifecycleOwner) { notes ->
            when (notes) {
                is RequestState.Loading -> {}
                is RequestState.Success -> {
                    Toast.makeText(
                        requireContext(),
                        notes.value,
                        Toast.LENGTH_SHORT,
                    ).show()
                }
                is RequestState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        notes.errorMessage,
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
        }
    }

    private fun showFilePicker() {
        try {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "application/json"
            }

            getContent.launch(intent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                requireContext(),
                "There are no file explorer clients installed.",
                Toast.LENGTH_SHORT,
            ).show()
        }
    }
}
