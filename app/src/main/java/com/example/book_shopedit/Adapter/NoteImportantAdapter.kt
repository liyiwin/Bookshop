package com.example.book_shopedit.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.book_shopedit.Data.my_data_book_note
import com.example.book_shopedit.R

class NoteImportantAdapter(val context: Context, var outer_list:MutableList<my_data_book_note>): RecyclerView.Adapter<NoteImportantAdapter.ViewHolder>()  {

    var inner_list = mutableListOf<my_data_book_note>()

    var status = 0

    init {

        inner_list = outer_list

    }

    var this_important_id:Important_id?= null


    interface Important_id {

        fun this_important(ReadingInfo_id:Int,position:Int){


        }

    }

    fun my_note_id(my_important_note:Important_id){

        this_important_id = my_important_note

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteImportantAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_note,parent,false)

        return ViewHolder(view)
    }
    override fun getItemCount(): Int = inner_list.size

    override fun onBindViewHolder(holder: NoteImportantAdapter.ViewHolder, position: Int) {

        Glide.with(context).load(inner_list[position].image).into(holder.my_note_img)

        holder.my_note_title.text = inner_list[position].title

        holder.my_note_date.text = inner_list[position].created_at

        if (inner_list[position].is_mark == 0) {

            holder.my_note_star.setImageResource(R.drawable.star_two)



        } else {

            holder.my_note_star.setImageResource(R.drawable.star)


        }


        holder.my_note_star.setOnClickListener {

            this_important_id?.this_important(inner_list[position].reading_info_id,position)


        }

    }
    inner class  ViewHolder (view: View):RecyclerView.ViewHolder(view){

        val my_note_img = view.findViewById<ImageView>(R.id.my_note_img)

        val my_note_title = view.findViewById<TextView>(R.id.my_note_title)

        val my_note_date = view.findViewById<TextView>(R.id.my_note_date)

        val my_note_star = view.findViewById<ImageView>(R.id.my_note_star)

        val my_container = view.findViewById<ConstraintLayout>(R.id.my_container)


    }

    fun update (list:MutableList<my_data_book_note>){

        inner_list = list

        notifyDataSetChanged()

    }
}