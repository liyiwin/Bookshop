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

class Note_Adapter(val context: Context, var outer_list:MutableList<my_data_book_note>):
    RecyclerView.Adapter<Note_Adapter.ViewHolder>() {

    var inner_list = mutableListOf<my_data_book_note>()

    init {

        inner_list = outer_list

    }

    var this_click_implement:Click?= null

    var this_note_id:Note_id?= null


    interface Note_id {

        fun this_id(id:Int){


        }

    }

    fun my_note_id(my_note:Note_id){

        this_note_id = my_note

    }


    interface Click {

        fun this_click(read_info:Int,read_content:Int){

        }

    }

    fun my_click(my_note_implement:Click){

        this_click_implement = my_note_implement

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Note_Adapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_note,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = inner_list.size

    override fun onBindViewHolder(holder: Note_Adapter.ViewHolder, position: Int) {

        Glide.with(context).load(inner_list[position].image).into(holder.my_note_img)

        holder.my_note_title.text = inner_list[position].title

        holder.my_note_date.text = inner_list[position].created_at

        if (inner_list[position].is_mark == 0){

            holder.my_note_star.setImageResource(R.drawable.star_two)

        }

        else{

            holder.my_note_star.setImageResource(R.drawable.star)

        }


        holder.my_note_star.setOnClickListener {

            if (inner_list[position].is_mark == 0){

                this_note_id?.this_id(inner_list[position].reading_info_id)

                holder.my_note_star.setImageResource(R.drawable.star)

                inner_list[position].is_mark = 1

                notifyDataSetChanged()

            }

            else {

                this_note_id?.this_id(inner_list[position].reading_info_id)

                holder.my_note_star.setImageResource(R.drawable.star_two)

                inner_list[position].is_mark = 0

                notifyDataSetChanged()

            }



        }


        holder.my_container.setOnClickListener {

            this_click_implement?.this_click(inner_list[position].reading_info_id,inner_list[position].reading_content_id)

        }


    }



    inner class ViewHolder (view: View):RecyclerView.ViewHolder(view){

        val my_note_img = view.findViewById<ImageView>(R.id.my_note_img)

        val my_note_title = view.findViewById<TextView>(R.id.my_note_title)

        val my_note_date = view.findViewById<TextView>(R.id.my_note_date)

        val my_note_star = view.findViewById<ImageView>(R.id.my_note_star)

        val my_container = view.findViewById<ConstraintLayout>(R.id.my_container)

    }

    fun  update (list:MutableList<my_data_book_note>){

        inner_list = list

        notifyDataSetChanged()

    }
}