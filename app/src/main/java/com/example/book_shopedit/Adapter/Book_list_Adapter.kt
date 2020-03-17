package com.example.book_shopedit.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.book_shopedit.Data.my_data_book_list
import com.example.book_shopedit.Data.my_data_category
import com.example.book_shopedit.R
import kotlinx.android.synthetic.main.item_ten.view.*

class Book_list_Adapter (val context: Context, var outer_list:MutableList<my_data_book_list>):
    RecyclerView.Adapter<Book_list_Adapter.ViewHolder>()
{

    var inner_list = mutableListOf<my_data_book_list>()

    init {
        inner_list = outer_list

    }

    var this_book_id:Book_id? = null


    interface Book_id {

        fun this_id(id:Int,title:String){

        }

    }

    fun my_book_id (my_book:Book_id){

        this_book_id = my_book


    }


    /*-------------------------------------*/


    var this_book_id_add:Book_id_add? = null


    interface Book_id_add {

        fun this_id_add(id_add:Int){

        }


    }

    fun my_book_id_add (my_book_add:Book_id_add){

        this_book_id_add = my_book_add


    }


    /*-------------------------------------*/

    var this_book_id_delete:Book_id_delete? = null


    interface Book_id_delete {

        fun this_id_delete(id_delete:Int){

        }

    }

    fun my_book_id_delete (my_book_delete:Book_id_delete){

        this_book_id_delete = my_book_delete


    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Book_list_Adapter.ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_ten,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = inner_list.size

    override fun onBindViewHolder(holder: Book_list_Adapter.ViewHolder, position: Int) {

        if(inner_list[position].is_in_like == true){

            //inner_list[position].is_in_like = false

            holder.book_list_likecell.setImageResource(R.drawable.heart_pink)


        }

        else{


            holder.book_list_likecell.setImageResource(R.drawable.heart_white)


        }

        holder.book_list_titlecell.text = inner_list[position].title

        holder.book_list_authorcell.text =inner_list[position].author

        holder.book_list_introcell.text = inner_list[position].intro

        holder.book_list_comment.text = inner_list[position].comment_score.toString()

        holder.book_list_likecell.setOnClickListener {

            if(inner_list[position].is_in_like == true){

                holder.book_list_likecell.setImageResource(R.drawable.heart_white)

                this_book_id_delete?.this_id_delete(inner_list[position].book_id)

            }


            else{

                holder.book_list_likecell.setImageResource(R.drawable.heart_pink)

                this_book_id_add?.this_id_add(inner_list[position].book_id)


            }

        }

        holder.book_list_layout.setOnClickListener {

            this_book_id?.this_id(inner_list[position].book_id,inner_list[position].title)

        }

        Glide.with(context)
            .load(inner_list[position].image)
            .into(holder.book_list_image)

    }

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){

        val book_list_image = view.findViewById<ImageView>(R.id.book_list_image)

        val book_list_titlecell = view.findViewById<TextView>(R.id.book_list_titlecell)

        val book_list_authorcell = view.findViewById<TextView>(R.id.book_list_authorcell)

        val book_list_introcell = view.findViewById<TextView>(R.id.book_list_introcell)

        val book_list_comment = view.findViewById<TextView>(R.id.my_book_comment)

        val book_list_likecell =view.findViewById<ImageView>(R.id.book_list_likecell)

        val book_list_layout = view.book_list_layout

    }

    fun update(list:MutableList<my_data_book_list>){

        inner_list = list

        notifyDataSetChanged()

    }
}