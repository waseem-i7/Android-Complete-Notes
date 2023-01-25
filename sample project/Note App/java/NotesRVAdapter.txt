package com.mwi7.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*

class NotesRVAdapter(val context : Context, val listner : INotesRVAdapter)  : RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {

    val allNotes = ArrayList<Note>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) {
            itemView.textView.text = note.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       val viewHolder = NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false))

        viewHolder.itemView.deleteButton.setOnClickListener {
            listner.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
       holder.bind(allNotes[position])
    }

    override fun getItemCount(): Int {
       return allNotes.size
    }

    fun updateList(newList : List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    interface  INotesRVAdapter{
        fun onItemClicked(note: Note)
    }
}