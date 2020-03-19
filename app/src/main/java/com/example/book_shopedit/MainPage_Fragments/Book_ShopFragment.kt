package com.example.book_shopedit.MainPage_Fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.book_shopedit.Adapter.BookShop_Adapter
import com.example.book_shopedit.BookShop_Page.Book_list_twoActivity
import com.example.book_shopedit.Data.my_data_book_theme
import com.example.book_shopedit.Data.my_data_booktheme_list_two
import com.example.book_shopedit.Data.my_data_category
import com.example.book_shopedit.Model.add_bookcategory
import com.example.book_shopedit.Model.add_booktheme
import com.example.book_shopedit.Model.get_theme_books

import com.example.book_shopedit.R
import com.example.book_shopedit.ViewModel.ViewModel_TotoalSort
import kotlinx.android.synthetic.main.fragment_book__shop.*
import kotlinx.android.synthetic.main.fragment_book__shop.view.*

/**
 * A simple [Fragment] subclass.
 */

class Book_ShopFragment : Fragment() {

    lateinit var my_adapter : BookShop_Adapter

    var my_category:MutableList<my_data_category> = mutableListOf<my_data_category>()

    val my_themelist = mutableListOf<my_data_booktheme_list_two>()

    var my_global_id:Int = 0

    var my_global_title = ""

    var my_newbook_id:Int = 0

    var my_newbook_title = ""

    var my_freebook_id:Int =0

    var my_freebook_title = ""

    var my_one_id:Int =0

    var my_one_title = ""

    var my_two_id:Int =0

    var my_two_title = ""

    var my_three_id:Int =0

    var my_three_title = ""

    var my_four_id:Int =0

    var my_four_title = ""

    var my_five_id:Int =0

    var my_five_title = ""

    private val viewModel by lazy {

        ViewModelProviders.of(this).get(ViewModel_TotoalSort::class.java)
    }

    lateinit var rootView:View

    var my_theme = mutableListOf<my_data_book_theme>()

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

        add_bookcategory(viewModel,activity!!)

        add_booktheme(viewModel,activity!!)

        val my_category_Observer = Observer<MutableList<my_data_category>> {

            my_category = it

            my_adapter.update_category_list(my_category)
        }

        viewModel.get_my_category().observe(this,my_category_Observer)

        val my_theme_Observer = Observer<MutableList<my_data_book_theme>>{

            my_theme = it

            val my_global_theme = my_theme.filter { it.theme_name == "服務｜代訂英文書" }

            my_global_id = my_global_theme.get(0).id

            my_global_title = my_global_theme.get(0).theme_name

            val  my_freebook_theme = my_theme.filter { it.theme_name == "主題推薦"}

            my_freebook_id = my_freebook_theme.get(0).id

            my_freebook_title = my_freebook_theme.get(0).theme_name

            val my_newbook_theme = my_theme.filter { it.theme_name =="套件滿5千送撲克牌" }

            my_newbook_id = my_newbook_theme.get(0).id

            my_newbook_title = my_newbook_theme.get(0).theme_name

            val my_theme_one = my_theme.filter{ it.theme_name =="IT狗精品區" }

            my_one_id = my_theme_one.get(0).id

            my_one_title = my_theme_one.get(0).theme_name

            val my_theme_two = my_theme.filter { it.theme_name =="推薦｜軟體開發聖經" }

            my_two_id = my_theme_two.get(0).id

            my_two_title = my_theme_two.get(0).theme_name

            val my_theme_three = my_theme.filter { it.theme_name =="主題｜設計模式" }

            my_three_id = my_theme_three.get(0).id

            my_three_title = my_theme_three.get(0).theme_name

            val my_theme_four = my_theme.filter { it.theme_name =="無瑕的程式碼超值合購" }

            my_four_id = my_theme_four.get(0).id

            my_four_title = my_theme_four.get(0).theme_name

            val my_theme_five = my_theme.filter { it.theme_name =="感的資料科學家" }

            my_five_id = my_theme_five.get(0).id

            my_five_title = my_theme_five.get(0).theme_name

            get_theme_books(my_one_id,my_one_title,my_themelist)

            get_theme_books(my_two_id,my_two_title,my_themelist)

            get_theme_books(my_three_id,my_three_title,my_themelist)

            get_theme_books(my_four_id,my_four_title,my_themelist)

            get_theme_books(my_five_id,my_five_title,my_themelist)

            my_adapter.update_themelist(my_themelist)

        }

        viewModel.get_my_theme().observe(this,my_theme_Observer)

        my_adapter.my_click(object:BookShop_Adapter.Onclick{

            override fun click(number: Int) {

                if (number == 1){

                    val intent = Intent(context, Book_list_twoActivity::class.java)

                    intent.putExtra("id",my_freebook_id)

                    intent.putExtra("title",my_freebook_title)

                    context!!.startActivity(intent)


                }

                else if (number ==2) {

                    val intent = Intent(context, Book_list_twoActivity::class.java)

                    intent.putExtra("id",my_global_id)

                    intent.putExtra("title",my_global_title)

                    context!!.startActivity(intent)


                }

                else{

                    val intent = Intent(context, Book_list_twoActivity::class.java)

                    intent.putExtra("id",my_newbook_id)

                    intent.putExtra("title",my_newbook_title)

                    context!!.startActivity(intent)

                }

            }


        })

        return rootView
    }

}
