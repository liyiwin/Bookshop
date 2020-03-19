package com.example.book_shopedit.BookShop_Page

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.RatingBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.book_shopedit.Model.*
import com.example.book_shopedit.R
import com.example.book_shopedit.ViewModel.ViewModel_BookDetail
import com.example.book_shopedit.sharedPreference.sharedPreference_login
import kotlinx.android.synthetic.main.activity_book__detail.*

class Book_DetailActivity : AppCompatActivity() {

    lateinit var adapter : BookInformation_Adapter

    private val viewModel by lazy {

        ViewModelProviders.of(this).get(ViewModel_BookDetail::class.java)

    }

    private var likelist_status = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book__detail)

        setupView()

        val pref = sharedPreference_login(this)

        val id = intent.getIntExtra("id",0).toString()

        val title = intent.getStringExtra("title")

        val apitoken = pref.get_apitoken()

        my_book_detail_title.text = title

        get_book_detail(this,id,viewModel)

        get_book_publish(this,id,viewModel)

        existOrnot_in_wishlist(this,id,apitoken!!,viewModel)


        val liklist_Observer = Observer<String>{

            likelist_status = it

           if (it == "not in wishlist"){

              Glide.with(this).load(R.drawable.heart_white).into(mylikecell)

           }

            if (it == "in wishlist"){

               Glide.with(this).load(R.drawable.heart_pink).into(mylikecell)

           }

        }

        viewModel.get_wishlist_status().observe(this,liklist_Observer)


        val ima_Observer  = Observer<String>{

            Glide.with(this).load(it).into(my_book_detail_image)

        }

        viewModel.get_image().observe(this,ima_Observer)

        val score_Observer = Observer<String>{

            ratingBar3.rating = it.toFloat()

        }

        viewModel.get_comment_score().observe(this,score_Observer)

        val price_Observer = Observer<String>{

            my_book_detail_price.text = "價錢 : "+it

        }

        viewModel.get_price().observe(this,price_Observer)


        val author_Observer = Observer<String>{

            my_book_detail_author.text = "作者 : "+it

        }

        viewModel.get_author().observe(this,author_Observer)


        ratingBar3.onRatingBarChangeListener = object : RatingBar.OnRatingBarChangeListener{
            override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, fromUser: Boolean) {

                val my_rating = rating.toString()

                rating_book(id,apitoken,my_rating,this@Book_DetailActivity,viewModel)

            }

        }


        mylikecell.setOnClickListener {

            if (likelist_status == "not in wishlist"){

                add_book_into_wishlist(id,apitoken,this,viewModel)

            }

            else{

                delete_book_from_wishlist(id,apitoken,this,viewModel)

            }

        }

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
