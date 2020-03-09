package com.example.book_shopedit.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.book_shopedit.Data.my_data_good_sentence
import com.example.book_shopedit.Data.my_data_good_sentence_edit
import com.example.book_shopedit.R

class NoteEditAdapter(val context: Context, var outer_list:MutableList<my_data_good_sentence_edit>):
    RecyclerView.Adapter<NoteEditAdapter.ViewHolder>() {

    var innerlist = mutableListOf<my_data_good_sentence_edit>()

    init {
        innerlist = outer_list
    }

    var this_edit_implement:Edit?= null


    interface Edit {

        fun this_edit(info_content_id:Int,position: Int){


        }

    }

    fun my_edit(my_edit_implement:Edit){

        this_edit_implement = my_edit_implement

    }

    var this_delete_implement:Delete?= null


    interface Delete  {

        fun this_Delete(info_content_id:Int,position: Int){


        }

    }

    fun my_delete(my_delete_implement:Delete){

        this_delete_implement = my_delete_implement

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteEditAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_sentence_edit,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = innerlist.size

    override fun onBindViewHolder(holder: NoteEditAdapter.ViewHolder, position: Int) {

        holder.my_page_cell.text = "Page:${innerlist[position].page.toString()}"

        holder.my_sentence_cell.text = innerlist[position].sentence



        holder.my_sentence_edit.setOnClickListener {

            this_edit_implement?.this_edit(innerlist[position].id,position)

        }

        holder.my_sentence_delete.setOnClickListener {

            this_delete_implement?.this_Delete(innerlist[position].id,position)

        }

    }


    inner class ViewHolder (view: View):RecyclerView.ViewHolder(view){

        val my_sentence_cell = view.findViewById<TextView>(R.id.my_sentence_edit_cell)

        val my_page_cell  = view.findViewById<TextView>(R.id.my_page_edit_cell)

        val my_sentence_edit = view.findViewById<ImageView>(R.id.my_sentence_edit)

        val my_sentence_delete = view.findViewById<ImageView>(R.id.my_sentence_delete)


    }

    fun update (list:MutableList<my_data_good_sentence_edit>){

      innerlist = list

      notifyDataSetChanged()

    }
}