package com.example.book_shopedit.BookShop_Page

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.book_shopedit.R
import kotlinx.android.synthetic.main.activity_book__detail.*

class Book_DetailActivity : AppCompatActivity() {

    lateinit var adapter : BookInformation_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book__detail)

        setupView()
    }

    private fun setupView() {

        adapter = BookInformation_Adapter(supportFragmentManager)

        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        my_book_information_container.adapter = adapter

        tabLayout2.setupWithViewPager( my_book_information_container)
    }

    inner class BookInformation_Adapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager){
        override fun getItem(position: Int): Fragment {
            when(position){

                0 ->return  BookdetailIntroduceFragment()

                else->return BookdetailPublicFragment()


            }
        }

        override fun getCount(): Int = 2


        override fun getPageTitle(position: Int): CharSequence {
            when(position){
                0 -> return "書籍簡介"
                else-> return "出版社資訊"

            }
        }
    }
}
