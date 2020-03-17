package com.example.book_shopedit.BookShop_Page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.book_shopedit.Adapter.Category_Adapter
import com.example.book_shopedit.Data.my_data_category
import com.example.book_shopedit.Model.add_bookcategory
import com.example.book_shopedit.R
import com.example.book_shopedit.ViewModel.ViewModel_AddNote
import com.example.book_shopedit.ViewModel.ViewModel_TotoalSort
import kotlinx.android.synthetic.main.activity_totoal_sort.*

class TotoalSortActivity : AppCompatActivity() {

    private val  viewModel_addCategory by lazy { ViewModelProviders.of(this
    ).get(ViewModel_TotoalSort::class.java) }

    var my_category:MutableList<my_data_category> = ArrayList<my_data_category>()

    private var my_Adapter = Category_Adapter(this@TotoalSortActivity,my_category)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_totoal_sort)

        totoal_sort_RecyclerView.layoutManager = GridLayoutManager(this@TotoalSortActivity,2)

        totoal_sort_RecyclerView.adapter = my_Adapter


        add_bookcategory (viewModel_addCategory,this)

        val category_Observer = Observer<MutableList<my_data_category>>{

             my_Adapter.update(it)

        }

        viewModel_addCategory.get_my_category().observe(this,category_Observer)

        my_Adapter.my_id(object:Category_Adapter.Category_id{

            override fun this_id(id:Int,title:String) {

                val intent = Intent(this@TotoalSortActivity,Book_listActivity::class.java)

                intent.putExtra("id",id)

                intent.putExtra("title",title)

                startActivity(intent)

            }

        } )

    }
}
