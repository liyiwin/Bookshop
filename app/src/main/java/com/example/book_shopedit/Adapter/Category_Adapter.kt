package com.example.book_shopedit.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.book_shopedit.Data.my_data_category
import com.example.book_shopedit.R

class Category_Adapter(var context: Context, var list:MutableList<my_data_category>): RecyclerView.Adapter<Category_Adapter.ViewHolder>() {

    var innerlist:MutableList<my_data_category> =ArrayList<my_data_category>()

    init {

        innerlist = list

    }


    var this_Category_id:Category_id? = null


    interface Category_id {

        fun this_id(id:Int,title:String){

        }

    }

    fun my_id (my_categoryid:Category_id){

        this_Category_id = my_categoryid

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Category_Adapter.ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_night,parent,false)

        return ViewHolder(view)

    }

    override fun getItemCount(): Int =  innerlist.size

    override fun onBindViewHolder(holder: Category_Adapter.ViewHolder, position: Int) {

        holder.text.text = innerlist[position].category_name

        holder.layout.setOnClickListener {

            this_Category_id?.this_id(innerlist[position].is_category,innerlist[position].category_name)

            Log.e("this","this")

        }


    }

    inner class  ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val text = view.findViewById<TextView>(R.id.totoal_sort_text)

        val layout = view.findViewById<RelativeLayout>(R.id.layou)

    }


    fun update(list:MutableList<my_data_category>){

        innerlist = list

        notifyDataSetChanged()

    }
}