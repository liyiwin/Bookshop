package com.example.book_shopedit.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.book_shopedit.BookShop_Page.Book_DetailActivity
import com.example.book_shopedit.Data.my_data_booktheme_list_two
import com.example.book_shopedit.R
import kotlinx.android.synthetic.main.viewpage_item_two.view.*

class My_ViewPageSingle_Adapter(val context: Context?, val list: List<my_data_booktheme_list_two>): PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = 5

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(context).inflate(R.layout.viewpage_item_two, container, false)

        when(position){

            0 ->{

                view.my_theme2.text = list[0].theme_name

                view.book_name.text = list[0].title

                view.book_author.text = list[0].author

                view.book_introduce.text = list[0].intro

                Glide.with(context!!).load(list[0].image).into(view.single_book)


                view.single_book.setOnClickListener {

                    val intent = Intent(context, Book_DetailActivity::class.java)

                    intent.putExtra("id",list[0].book_id)

                    intent.putExtra("title",list[0].title)

                    context.startActivity(intent)


                }

            }

            1 ->{

                view.my_theme2.text = list[0].theme_name

                view.book_name.text = list[1].title

                view.book_author.text = list[1].author

                view.book_introduce.text = list[1].intro

                Glide.with(context!!).load(list[1].image).into(view.single_book)

                view.single_book.setOnClickListener {

                    val intent = Intent(context, Book_DetailActivity::class.java)

                    intent.putExtra("id",list[1].book_id)

                    intent.putExtra("title",list[0].title)

                    context.startActivity(intent)

                }

            }

            2 ->{


                view.my_theme2.text = list[0].theme_name

                view.book_name.text = list[2].title

                view.book_author.text = list[2].author

                view.book_introduce.text = list[2].intro

                Glide.with(context!!).load(list[2].image).into(view.single_book)

                view.single_book.setOnClickListener {

                    val intent = Intent(context,Book_DetailActivity::class.java)

                    intent.putExtra("id",list[2].book_id)

                    intent.putExtra("title",list[0].title)

                    context.startActivity(intent)

                }



            }

            3 ->{

                view.my_theme2.text = list[0].theme_name

                view.book_name.text = list[3].title

                view.book_author.text = list[3].author

                view.book_introduce.text = list[3].intro

                Glide.with(context!!).load(list[3].image).into(view.single_book)

                view.single_book.setOnClickListener {

                    val intent = Intent(context,Book_DetailActivity::class.java)

                    intent.putExtra("id",list[3].book_id)

                    intent.putExtra("title",list[0].title)

                    context.startActivity(intent)

                }


            }

            4 ->{


                view.my_theme2.text = list[0].theme_name

                view.book_name.text = list[4].title

                view.book_author.text = list[4].author

                view.book_introduce.text = list[4].intro

                Glide.with(context!!).load(list[4].image).into(view.single_book)

                view.single_book.setOnClickListener {

                    val intent = Intent(context,Book_DetailActivity::class.java)

                    intent.putExtra("id",list[4].book_id)

                    intent.putExtra("title",list[0].title)

                    context.startActivity(intent)

                }


            }



        }



        container.addView(view)

        return  view


    }
}