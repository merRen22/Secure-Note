package com.challenge.get.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.challenge.get.base.util.formatDate
import com.challenge.get.home.databinding.ItemNoteBinding
import com.challenge.get.model.Note
import java.util.ArrayList

class NoteAdapter(
    val onItemPressed: (Int) -> Unit,
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private val notes: MutableList<Note>

    override fun getItemCount(): Int = notes.size

    init {
        notes = ArrayList()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false),
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    inner class ViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {
        var binding = ItemNoteBinding.bind(view)

        fun bind(note: Note) {
            binding.tvTitle.text = note.title
            binding.tvMessage.text = note.description
            binding.tvCreatedAt.text = note.creationDate.formatDate()
            binding.root.setOnClickListener {
                onItemPressed(note.id)
            }
        }
    }

    fun setDataChange(notes: List<Note>) {
        this.notes.clear()
        this.notes.addAll(notes)
        notifyItemRangeInserted(0, notes.size)
    }

    fun setDataFiltered(notes: List<Note>) {
        this.notes.clear()
        this.notes.addAll(notes)
        notifyDataSetChanged()
    }
}
