package com.example.book_shopedit.MainPage_Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.book_shopedit.Adapter.BookShop_Adapter
import com.example.book_shopedit.Data.my_data_booktheme_list_two
import com.example.book_shopedit.Data.my_data_category

import com.example.book_shopedit.R
import kotlinx.android.synthetic.main.fragment_book__shop.*
import kotlinx.android.synthetic.main.fragment_book__shop.view.*

/**
 * A simple [Fragment] subclass.
 */



class Book_ShopFragment : Fragment() {

    lateinit var  my_adapter : BookShop_Adapter

    var my_category:MutableList<my_data_category> = mutableListOf<my_data_category>()

    val my_themelist = mutableListOf<my_data_booktheme_list_two>()

    lateinit var rootView:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        my_adapter = BookShop_Adapter(context!!,my_themelist,my_category)

        rootView = inflater.inflate(R.layout.fragment_book__shop, container, false)

        rootView.my_book_recyclerview.apply {

          adapter = my_adapter

          layoutManager = LinearLayoutManager(context)

        }

        return rootView
    }




}
