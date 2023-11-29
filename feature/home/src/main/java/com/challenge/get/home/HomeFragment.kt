package com.challenge.get.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.toArgb
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.challenge.get.base.AppConstants
import com.challenge.get.base.compose.ChallengeTheme
import com.challenge.get.repository.util.RequestState
import com.challenge.get.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var navController: NavController

    lateinit var binding: FragmentHomeBinding

    private val homeViewModel: HomeViewModel by viewModels()

    private val noteAdapter = NoteAdapter { noteId ->
        navigateToDetail(noteId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(
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
                HomeHeader(
                    onSearchClick = { searchTerm ->
                        homeViewModel.filterNotes(searchTerm)
                    },
                    onSettingsClick = {
                        if (::navController.isInitialized) {
                            navController.navigate(R.id.action_homeFragment_to_settingsFragment)
                        }
                    },
                )
            }
        }

        binding.btnAddNote.setOnClickListener {
            if (::navController.isInitialized) {
                navController.navigate(R.id.action_homeFragment_to_settingsFragment)
            }
        }

        binding.btnAddNote.setOnClickListener {
            navigateToNewNote()
        }

        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotes.adapter = noteAdapter

        fetchNotes()
    }

    private fun fetchNotes() {
        homeViewModel.mutableNotes.observe(viewLifecycleOwner) { notes ->
            if (notes.isEmpty()) {
                binding.emptySearchMessage.visibility = View.VISIBLE
                binding.rvNotes.visibility = View.GONE
            } else {
                binding.rvNotes.visibility = View.VISIBLE
                binding.emptySearchMessage.visibility = View.GONE
                noteAdapter.setDataFiltered(notes)
            }
        }

        homeViewModel.fetchNotes().observe(viewLifecycleOwner) { notes ->
            when (notes) {
                is RequestState.Loading -> {
                    binding.progressIndicator.visibility = View.VISIBLE
                    binding.emptySearchMessage.visibility = View.GONE
                    binding.rvNotes.visibility = View.GONE
                }
                is RequestState.Success -> {
                    binding.progressIndicator.visibility = View.GONE
                    binding.btnAddNote.visibility = View.VISIBLE
                    if (notes.value.isEmpty()) {
                        binding.emptyMessage.visibility = View.VISIBLE
                    } else {
                        binding.rvNotes.visibility = View.VISIBLE
                        noteAdapter.setDataChange(notes.value)
                    }
                }
                is RequestState.Error -> {
                    // Todo show retry button
                }
            }
        }
    }

    private fun navigateToDetail(noteId: Int) {
        val request = NavDeepLinkRequest.Builder.fromUri(
            (AppConstants.DETAIL_DEEP_LINK + noteId).toUri(),
        ).build()

        if (::navController.isInitialized) {
            navController.navigate(request)
        }
    }

    private fun navigateToNewNote() {
        val request = NavDeepLinkRequest.Builder.fromUri(
            (AppConstants.DETAIL_DEEP_LINK).toUri(),
        ).build()

        if (::navController.isInitialized) {
            navController.navigate(request)
        }
    }
}
