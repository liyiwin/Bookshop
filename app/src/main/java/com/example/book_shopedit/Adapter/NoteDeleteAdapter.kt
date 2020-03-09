package com.example.book_shopedit.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.book_shopedit.Data.my_data_book_note
import com.example.book_shopedit.R
import kotlinx.android.synthetic.main.item_delete.view.*

class NoteDeleteAdapter(val context: Context, var outer_list:MutableList<my_data_book_note>): RecyclerView.Adapter<NoteDeleteAdapter.ViewHolder>() {

    var inner_list = mutableListOf<my_data_book_note>()

    init {

        inner_list = outer_list

    }

    var this_delete_id:Delete_id?= null


    interface Delete_id {

        fun this_delete(ReadingInfo_id:Int,position:Int){


        }

    }

    fun my_note_id(my_delete_note:Delete_id){

        this_delete_id = my_delete_note

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteDeleteAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_delete,parent,false)


        return ViewHolder(view)
    }

    override fun getItemCount(): Int = inner_list.size

    override fun onBindViewHolder(holder: NoteDeleteAdapter.ViewHolder, position: Int) {

        Glide.with(context).load(inner_list[position].image).into(holder.my_delete_img)

        holder.my_note_title_delete.text = inner_list[position].title

        holder.my_deletenote_date.text = inner_list[position].created_at

        holder.my_note_delete.setOnClickListener {


            this_delete_id?.this_delete(inner_list[position].reading_info_id,position)


        }


    }



    inner class  ViewHolder (view: View):RecyclerView.ViewHolder(view){

        val my_delete_img = view.findViewById<ImageView>(R.id.my_delete_img)

        val my_note_title_delete = view.findViewById<TextView>(R.id.my_note_title_delete)

        val my_note_delete = view.findViewById<ImageView>(R.id.my_note_delete)

        val my_deletenote_date = view.my_deletenote_date


    }


    fun update (list:MutableList<my_data_book_note>) {

        inner_list = list

        notifyDataSetChanged()


    }
}