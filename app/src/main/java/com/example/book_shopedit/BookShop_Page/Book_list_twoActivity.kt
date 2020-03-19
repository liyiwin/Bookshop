package com.example.book_shopedit.BookShop_Page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.book_shopedit.Adapter.booktheme_list_Adapter
import com.example.book_shopedit.Data.my_data_booktheme_list
import com.example.book_shopedit.Model.add_into_themewishlist
import com.example.book_shopedit.Model.filter_themewishlist
import com.example.book_shopedit.Model.remove_from_themewishlist
import com.example.book_shopedit.R
import com.example.book_shopedit.ViewModel.ViewModel_Total_list
import com.example.book_shopedit.sharedPreference.sharedPreference_login
import kotlinx.android.synthetic.main.activity_book_list.*
import kotlinx.android.synthetic.main.activity_book_list_two.*
import kotlinx.android.synthetic.main.item_ten.view.*

class Book_list_twoActivity : AppCompatActivity() {


    var my_book_list = mutableListOf<my_data_booktheme_list>()

    val Adapter = booktheme_list_Adapter(this@Book_list_twoActivity,my_book_list)

    private val viewModel by lazy {

        ViewModelProviders.of(this).get(ViewModel_Total_list::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list_two)

        my_book_list_title_two.text = intent.getStringExtra("title")

        val theme =  intent.getIntExtra("id",0).toString()

        val pref = sharedPreference_login(this)

        val mytoken = pref.get_apitoken()


        book_list_recyclerview_two.apply {

            layoutManager = LinearLayoutManager(this@Book_list_twoActivity)

            adapter = Adapter



        }

        filter_themewishlist(viewModel,theme, mytoken!!,this)



        val total_list_Observer =  Observer<MutableList<my_data_booktheme_list>>{

            my_book_list = it

            val number = my_book_list.size

            Adapter.update(my_book_list)

        }

        viewModel.get_total_theme_list().observe(this,total_list_Observer)


        Adapter.my_book_id(object:booktheme_list_Adapter.Book_id{

            override fun this_id(id: Int, title: String) {
                val intent = Intent(this@Book_list_twoActivity,Book_DetailActivity::class.java)

                intent.putExtra("id",id)

                intent.putExtra("title",title)

                startActivity(intent)

                finish()

            }
        })


        Adapter.my_book_id_add(object:booktheme_list_Adapter.Book_id_add{

            override fun this_id_add(id_add: Int) {

                val id = id_add.toString()

                add_into_themewishlist(viewModel,id,mytoken,this@Book_list_twoActivity,my_book_list)

            }
        })


        Adapter.my_book_id_delete(object:booktheme_list_Adapter.Book_id_delete{

            override fun this_id_delete(id_delete: Int) {

                val id = id_delete.toString()


                remove_from_themewishlist(viewModel,id,mytoken,this@Book_list_twoActivity,my_book_list)

            }
        })


    }




}
