package com.example.book_shopedit.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.book_shopedit.Data.my_data_good_sentence
import com.example.book_shopedit.R

class NoteDataAdapter(val context: Context, var outer_list:MutableList<my_data_good_sentence>):
    RecyclerView.Adapter<NoteDataAdapter.ViewHolder>() {
    var innerlist = mutableListOf<my_data_good_sentence>()

    init {
        innerlist = outer_list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteDataAdapter.ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_sentence,parent, false)

        return ViewHolder(view)

    }

    override fun getItemCount(): Int  = innerlist.size

    override fun onBindViewHolder(holder: NoteDataAdapter.ViewHolder, position: Int) {

        holder.my_page_cell.text = "Page:${innerlist[position].page}"

        holder.my_sentence_cell.text = innerlist[position].sentence

    }


    inner class ViewHolder (view: View):RecyclerView.ViewHolder(view){

        val my_sentence_cell = view.findViewById<TextView>(R.id.my_sentence_cell)

        val my_page_cell  = view.findViewById<TextView>(R.id.my_page_cell)

    }

    fun update (list:MutableList<my_data_good_sentence>){

        innerlist = list

        notifyDataSetChanged()

    }


}