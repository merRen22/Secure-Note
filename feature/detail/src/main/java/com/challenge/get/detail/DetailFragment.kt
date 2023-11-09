package com.challenge.get.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.challenge.get.base.compose.ChallengeTheme
import com.challenge.get.base.util.RequestState
import com.challenge.get.detail.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var navController: NavController

    private lateinit var binding: FragmentDetailBinding

    private val detailViewModel: DetailViewModel by viewModels()

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailBinding.inflate(
            inflater,
            container,
            false,
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()

        val isNewNote = args.noteId == 0

        (activity as AppCompatActivity).supportActionBar?.apply {
            this.show()
            this.title = if (isNewNote) "New Note" else "Edit Note"
            this.setDisplayHomeAsUpEnabled(true)
            this.setDisplayShowHomeEnabled(true)
        }

        if (!isNewNote) findNote(args.noteId)

        binding.composeContainer.setContent {
            ChallengeTheme() {
                DetailScreen(
                    detailViewModel = detailViewModel,
                    onRegisterClick = {
                        if (args.noteId != 0) updateNote() else createNote()
                    },
                    onDeleteClick = {
                        deleteNote()
                    },
                )
            }
        }
    }

    private fun updateNote() {
        detailViewModel.updateNote().observe(viewLifecycleOwner) { note ->
            when (note) {
                is RequestState.Loading -> {}
                is RequestState.Error -> {}
                is RequestState.Success -> {
                    Toast.makeText(requireContext(), "Note updated", Toast.LENGTH_LONG).show()
                    moveBack()
                }
            }
        }
    }

    private fun createNote() {
        detailViewModel.createNote().observe(viewLifecycleOwner) { note ->
            when (note) {
                is RequestState.Loading -> {}
                is RequestState.Error -> {}
                is RequestState.Success -> {
                    Toast.makeText(requireContext(), "Note created", Toast.LENGTH_LONG).show()
                    moveBack()
                }
            }
        }
    }

    private fun deleteNote() {
        detailViewModel.deleteNote().observe(viewLifecycleOwner) { note ->
            when (note) {
                is RequestState.Loading -> {}
                is RequestState.Error -> {}
                is RequestState.Success -> {
                    Toast.makeText(requireContext(), note.value, Toast.LENGTH_LONG).show()
                    moveBack()
                }
            }
        }
    }

    private fun findNote(noteId: Int) {
        detailViewModel.findNote(noteId).observe(viewLifecycleOwner) { note ->
            when (note) {
                is RequestState.Loading -> {}
                is RequestState.Error -> {
                    // Todo redirect
                }
                is RequestState.Success -> {
                    detailViewModel.title.value = note.value.title
                    detailViewModel.description.value = note.value.description
                    detailViewModel.image.value = note.value.image
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    private fun moveBack() {
        if (::navController.isInitialized) {
            navController.popBackStack()
        }
    }
}
