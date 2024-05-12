package com.shatrudhan.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRvAdapter(
    val context: Context,
    val noteClickInterface: NoteClickInterface,
    val noteClickDeleteInterface: NoteClickDeleteInterface
) : RecyclerView.Adapter<NoteRvAdapter.ViewHolder>(){
    private val allNote = ArrayList<Note>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val noteTV = itemView.findViewById<TextView>(R.id.idTvNoteTitle)
        val timeTV = itemView.findViewById<TextView>(R.id.idTVtIMEsTAMP)
        val deleteTV = itemView.findViewById<ImageView>(R.id.idIvDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.note_rv_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return allNote.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTV.setText(allNote.get(position).noteTitle)
        holder.timeTV.setText("Last Updated : "+allNote.get(position).timestamp)

        holder.deleteTV.setOnClickListener{
            noteClickDeleteInterface.onDeleteIconClick(allNote.get(position))
        }
        holder.itemView.setOnClickListener{
            noteClickInterface.onNoteClick(allNote.get(position))
        }
    }
    fun updateList(newList: List<Note>) {
        allNote.clear()
        allNote.addAll(newList)
        notifyDataSetChanged()
    }
}
interface NoteClickDeleteInterface{
    fun onDeleteIconClick(note: Note)
}
interface NoteClickInterface{
    fun onNoteClick(note: Note)
}