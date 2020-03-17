package com.example.book_shopedit.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.book_shopedit.R
import kotlinx.android.synthetic.main.item_five.view.*

class My_ViewPageCircle_Adapter(val context: Context?): PagerAdapter()  {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = 5

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.item_five, container, false)


        when(position){

            0 ->{

                Glide.with(context!!).load(R.drawable.ad_seven).into(view.my_banner)


            }

            1 ->{

                Glide.with(context!!).load(R.drawable.ad_eight).into(view.my_banner)


            }

            2 ->{

                Glide.with(context!!).load(R.drawable.ad_history).into(view.my_banner)


            }

            3 ->{

                Glide.with(context!!).load(R.drawable.ad_four).into(view.my_banner)


            }

            4 ->{

                Glide.with(context!!).load(R.drawable.ad_five).into(view.my_banner)


            }


        }


        container.addView(view)
        return view
    }

}