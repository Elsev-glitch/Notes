package com.example.notes.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.model.AppNote
import kotlinx.android.synthetic.main.note_item.view.*

class MainAdapter:RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var mListNote = emptyList<AppNote>()

    class MainViewHolder(view:View):RecyclerView.ViewHolder(view){
        val noteName:TextView = view.note_name
        val noteText:TextView = view.note_text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return MainViewHolder(view)
    }

    override fun onViewAttachedToWindow(holder: MainViewHolder) {
        holder.itemView.setOnClickListener {
            MainFragment.click(mListNote[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MainViewHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }

    override fun getItemCount(): Int = mListNote.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.noteName.text = mListNote[position].name
        holder.noteText.text = mListNote[position].text
    }

    fun setList(list: List<AppNote>){
        mListNote = list
        notifyDataSetChanged()
    }
}