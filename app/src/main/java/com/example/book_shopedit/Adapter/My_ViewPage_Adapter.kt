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
import kotlinx.android.synthetic.main.viewpager_item.view.*

class My_ViewPage_Adapter(val context: Context?, val list: List<my_data_booktheme_list_two>): PagerAdapter(){

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view == `object`

    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(context).inflate(R.layout.viewpager_item, container, false)

        when(position) {

            0 -> {

                Glide.with(context!!).load(list[0].image).into(view.one_book)
                Glide.with(context).load(list[1].image).into(view.two_book)
                Glide.with(context).load(list[2].image).into(view.three_book)

                view.my_theme.text = list[0].theme_name
                view.one_introduce.text = list[0].title.toString()
                view.two_introduce.text = list[1].title.toString()
                view.three_introduce.text = list[2].title.toString()

                view.one_book.setOnClickListener {

                    val intent = Intent(context,Book_DetailActivity::class.java)

                    intent.putExtra("id",list[0].book_id)

                    intent.putExtra("title",list[0].title)

                    context.startActivity(intent)



                }

                view.two_book.setOnClickListener {

                    val intent = Intent(context, Book_DetailActivity::class.java)

                    intent.putExtra("id",list[1].book_id)

                    intent.putExtra("title",list[1].title)

                    context.startActivity(intent)



                }


                view.three_book.setOnClickListener {

                    val intent = Intent(context,Book_DetailActivity::class.java)

                    intent.putExtra("id",list[2].book_id)

                    intent.putExtra("title",list[2].title)


                    context.startActivity(intent)



                }


            }

            1 -> {
                Glide.with(context!!).load(list[3].image).into(view.one_book)
                Glide.with(context).load(list[4].image).into(view.two_book)
                Glide.with(context).load(list[5].image).into(view.three_book)


                view.my_theme.text = list[0].theme_name
                view.one_introduce.text = list[3].title.toString()
                view.two_introduce.text = list[4].title.toString()
                view.three_introduce.text = list[5].title.toString()



                view.one_book.setOnClickListener {

                    val intent = Intent(context,Book_DetailActivity::class.java)

                    intent.putExtra("id",list[3].book_id)

                    intent.putExtra("title",list[3].title)

                    context.startActivity(intent)



                }

                view.two_book.setOnClickListener {

                    val intent = Intent(context,Book_DetailActivity::class.java)

                    intent.putExtra("id",list[4].book_id)

                    intent.putExtra("title",list[4].title)

                    context.startActivity(intent)



                }


                view.three_book.setOnClickListener {

                    val intent = Intent(context,Book_DetailActivity::class.java)

                    intent.putExtra("id",list[5].book_id)

                    intent.putExtra("title",list[5].title)

                    context.startActivity(intent)

                }

            }

            2 ->{
                Glide.with(context!!).load(list[6].image).into(view.one_book)
                Glide.with(context).load(list[7].image).into(view.two_book)
                Glide.with(context).load(list[8].image).into(view.three_book)


                view.my_theme.text = list[0].theme_name
                view.one_introduce.text = list[6].title.toString()
                view.two_introduce.text = list[7].title.toString()
                view.three_introduce.text = list[8].title.toString()



                view.one_book.setOnClickListener {

                    val intent = Intent(context,Book_DetailActivity::class.java)

                    intent.putExtra("id",list[6].book_id)

                    intent.putExtra("title",list[6].title)

                    context.startActivity(intent)



                }

                view.two_book.setOnClickListener {

                    val intent = Intent(context,Book_DetailActivity::class.java)

                    intent.putExtra("id",list[7].book_id)

                    intent.putExtra("title",list[7].title)

                    context.startActivity(intent)



                }


                view.three_book.setOnClickListener {

                    val intent = Intent(context,Book_DetailActivity::class.java)

                    intent.putExtra("id",list[8].book_id)

                    intent.putExtra("title",list[8].title)

                    context.startActivity(intent)

                }

            }

            3 ->{
                Glide.with(context!!).load(list[9].image).into(view.one_book)
                Glide.with(context).load(list[10].image).into(view.two_book)
                Glide.with(context).load(list[11].image).into(view.three_book)


                view.my_theme.text = list[0].theme_name
                view.one_introduce.text = list[9].title.toString()
                view.two_introduce.text = list[10].title.toString()
                view.three_introduce.text = list[11].title.toString()



                view.one_book.setOnClickListener {

                    val intent = Intent(context,Book_DetailActivity::class.java)

                    intent.putExtra("id",list[9].book_id)

                    intent.putExtra("title",list[9].title)

                    context.startActivity(intent)



                }

                view.two_book.setOnClickListener {

                    val intent = Intent(context,Book_DetailActivity::class.java)

                    intent.putExtra("id",list[10].book_id)

                    intent.putExtra("title",list[10].title)

                    context.startActivity(intent)



                }


                view.three_book.setOnClickListener {

                    val intent = Intent(context,Book_DetailActivity::class.java)

                    intent.putExtra("id",list[11].book_id)

                    intent.putExtra("title",list[11].title)

                    context.startActivity(intent)

                }

            }

            4 ->{
                Glide.with(context!!).load(list[12].image).into(view.one_book)
                Glide.with(context).load(list[13].image).into(view.two_book)
                Glide.with(context).load(list[14].image).into(view.three_book)


                view.my_theme.text = list[0].theme_name
                view.one_introduce.text = list[12].title.toString()
                view.two_introduce.text = list[13].title.toString()
                view.three_introduce.text = list[14].title.toString()



                view.one_book.setOnClickListener {

                    val intent = Intent(context,Book_DetailActivity::class.java)

                    intent.putExtra("id",list[12].book_id)

                    intent.putExtra("title",list[12].title)

                    context.startActivity(intent)



                }

                view.two_book.setOnClickListener {

                    val intent = Intent(context,Book_DetailActivity::class.java)

                    intent.putExtra("id",list[13].book_id)

                    intent.putExtra("title",list[13].title)

                    context.startActivity(intent)



                }


                view.three_book.setOnClickListener {

                    val intent = Intent(context,Book_DetailActivity::class.java)

                    intent.putExtra("id",list[14].book_id)

                    intent.putExtra("title",list[14].title)

                    context.startActivity(intent)

                }

            }




        }
        container.addView(view)
        return view

    }

    override fun getCount(): Int {

        return 5
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


}