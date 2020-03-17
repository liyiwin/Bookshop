package com.example.book_shopedit.BookShop_Page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.book_shopedit.Adapter.Book_list_Adapter
import com.example.book_shopedit.Data.my_data_book_list
import com.example.book_shopedit.Model.add_into_wishlist
import com.example.book_shopedit.Model.filter_wishlist
import com.example.book_shopedit.Model.remove_from_wishlist
import com.example.book_shopedit.R
import com.example.book_shopedit.ViewModel.ViewModel_Total_list
import com.example.book_shopedit.sharedPreference.sharedPreference_login
import kotlinx.android.synthetic.main.activity_book_list.*

class Book_listActivity : AppCompatActivity() {

    var my_book_list:MutableList<my_data_book_list> = ArrayList<my_data_book_list>()

    private  val Adapter = Book_list_Adapter(this@Book_listActivity,my_book_list)

    private val viewModel by lazy {

        ViewModelProviders.of(this).get(ViewModel_Total_list::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        my_book_list_title.text = intent.getStringExtra("title")

        val category =  intent.getIntExtra("id",0).toString()

        val pref = sharedPreference_login(this)

        val mytoken = pref.get_apitoken()

        filter_wishlist(viewModel,category, mytoken!!,this)

        val total_list_Observer =  Observer<MutableList<my_data_book_list>>{

            my_book_list = it

            Adapter.update(my_book_list)

        }

        viewModel.get_total_list().observe(this,total_list_Observer)


        book_list_recyclerview.apply {

            layoutManager = LinearLayoutManager(this@Book_listActivity)

            adapter = Adapter

        }


        Adapter.my_book_id(object:Book_list_Adapter.Book_id{

            override fun this_id(id: Int, title: String) {

                val intent = Intent(this@Book_listActivity,Book_DetailActivity::class.java)

                intent.putExtra("id",id)

                intent.putExtra("title",title)

                startActivity(intent)

                finish()

            }
        })


        Adapter.my_book_id_add(object:Book_list_Adapter.Book_id_add{

            override fun this_id_add(id_add: Int) {

                val id = id_add.toString()

                add_into_wishlist(viewModel,id,mytoken,this@Book_listActivity,my_book_list)

            }
        })

        Adapter.my_book_id_delete(object:Book_list_Adapter.Book_id_delete{

            override fun this_id_delete(id_delete: Int) {

                val id = id_delete.toString()

                remove_from_wishlist(viewModel,id,mytoken,this@Book_listActivity,my_book_list)

            }
        })

    }
}
