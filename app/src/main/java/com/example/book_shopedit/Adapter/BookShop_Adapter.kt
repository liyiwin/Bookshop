package com.example.book_shopedit.Adapter

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.book_shopedit.BookShop_Page.Book_listActivity
import com.example.book_shopedit.BookShop_Page.TotoalSortActivity
import com.example.book_shopedit.Data.my_data_booktheme_list_two
import com.example.book_shopedit.Data.my_data_category
import com.example.book_shopedit.R

class BookShop_Adapter (val context: Context, val outer_theme_list: MutableList<my_data_booktheme_list_two>, val outer_category_list:MutableList<my_data_category>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{

        val TYPE_ONE:Int = 0
        val TYPE_TWO:Int = 1
        val TYPE_THREE:Int = 2
        val TYPE_FOUR:Int = 3
        val TYPE_FIVE:Int = 4
        val TYPE_SIX:Int = 5
        val TYPE_SEVEN:Int = 6

    }

    var inner_theme_list = mutableListOf<my_data_booktheme_list_two>()

    var inner_category_list = mutableListOf<my_data_category>()



    init {

        inner_theme_list = outer_theme_list

        inner_category_list = outer_category_list

    }




    val handler = Handler()

    var autoScrollRunnable: Runnable? = null

    var this_Onclick:Onclick? = null

    var currentItem = 0




    interface Onclick {

        fun click(number:Int){

        }

    }

    fun my_click (my_Onclick: Onclick){

        this_Onclick = my_Onclick


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerView.ViewHolder {

        when (viewType){

            // single viewpage

            TYPE_ONE -> {

                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_one,parent,false)

                return ViewHolder_One(view)}

            // more

            TYPE_TWO -> {

                //固定

                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_two,parent,false)

                return ViewHolder_Two(view)}

            // mutiple viewpage

            TYPE_THREE -> {

                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_three,parent,false)

                return ViewHolder_three(view)}

            // 按鈕列

            TYPE_FOUR -> {

                //固定

                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_four,parent,false)

                return ViewHolder_four(view)}

            // 廣告


            TYPE_FIVE -> {

                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_five,parent,false)

                return ViewHolder_five(view)}

            TYPE_SIX -> {

                //固定

                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_six,parent,false)

                return ViewHolder_six(view)}

            else ->{

                //固定

                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_seven,parent,false)

                return ViewHolder_seven(view)

            }


        }

    }

    override fun getItemViewType(position: Int): Int{

        return when(position){

            0 -> TYPE_SIX

            1 -> TYPE_SEVEN

            2 -> TYPE_FOUR

            3 -> TYPE_THREE

            4 -> TYPE_FIVE

            5 -> TYPE_ONE

            6 -> TYPE_THREE

            7 -> TYPE_FIVE

            8 -> TYPE_ONE

            else -> TYPE_THREE

        }

    }

    override fun getItemCount(): Int  = 9

    override fun onBindViewHolder(holder:RecyclerView.ViewHolder, position: Int) {

        if(holder is  ViewHolder_One){

            when(position){

                //

                5->{

                    val my_list = inner_theme_list.filter { it.theme_name == "推薦｜軟體開發聖經" }

                    if ( my_list.size > 0){

                        holder.single_viewPager.adapter = My_ViewPageSingle_Adapter(context,my_list)

                        holder.single_viewPager.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
                            override fun onPageScrollStateChanged(state: Int) {

                            }

                            override fun onPageScrolled(
                                position: Int,
                                positionOffset: Float,
                                positionOffsetPixels: Int

                            ) {


                                when(position){

                                    0 ->{holder.dotone.setImageResource(R.drawable.disable)

                                        holder.dottwo.setImageResource(R.drawable.enable)

                                        holder.dotthree.setImageResource(R.drawable.enable)

                                        holder.dotfour.setImageResource(R.drawable.enable)

                                        holder.dotfive.setImageResource(R.drawable.enable)


                                    }



                                    1 ->{holder.dotone.setImageResource(R.drawable.enable)

                                        holder.dottwo.setImageResource(R.drawable.disable)

                                        holder.dotthree.setImageResource(R.drawable.enable)

                                        holder.dotfour.setImageResource(R.drawable.enable)

                                        holder.dotfive.setImageResource(R.drawable.enable)


                                    }


                                    2->{

                                        holder.dotone.setImageResource(R.drawable.enable)

                                        holder.dottwo.setImageResource(R.drawable.enable)

                                        holder.dotthree.setImageResource(R.drawable.disable)

                                        holder.dotfour.setImageResource(R.drawable.enable)

                                        holder.dotfive.setImageResource(R.drawable.enable)


                                    }

                                    3->{

                                        holder.dotone.setImageResource(R.drawable.enable)

                                        holder.dottwo.setImageResource(R.drawable.enable)

                                        holder.dotthree.setImageResource(R.drawable.enable)

                                        holder.dotfour.setImageResource(R.drawable.disable)

                                        holder.dotfive.setImageResource(R.drawable.enable)


                                    }

                                    4->{

                                        holder.dotone.setImageResource(R.drawable.enable)

                                        holder.dottwo.setImageResource(R.drawable.enable)

                                        holder.dotthree.setImageResource(R.drawable.enable)

                                        holder.dotfour.setImageResource(R.drawable.enable)

                                        holder.dotfive.setImageResource(R.drawable.disable)



                                    }


                                }


                            }

                            override fun onPageSelected(position: Int) {


                            }


                         }

                        )

                    }

                }


                8->{

                    val my_list = inner_theme_list.filter { it.theme_name == "感的資料科學家" }

                    if ( my_list.size > 0) {

                        holder.single_viewPager.adapter =
                            My_ViewPageSingle_Adapter(context, my_list)

                        holder.single_viewPager.addOnPageChangeListener(object :
                            ViewPager.OnPageChangeListener {
                            override fun onPageScrollStateChanged(state: Int) {

                            }

                            override fun onPageScrolled(
                                position: Int,
                                positionOffset: Float,
                                positionOffsetPixels: Int
                            ) {

                                when (position) {

                                    0 -> {
                                        holder.dotone.setImageResource(R.drawable.disable)

                                        holder.dottwo.setImageResource(R.drawable.enable)

                                        holder.dotthree.setImageResource(R.drawable.enable)

                                        holder.dotfour.setImageResource(R.drawable.enable)

                                        holder.dotfive.setImageResource(R.drawable.enable)

                                    }


                                    1 -> {
                                        holder.dotone.setImageResource(R.drawable.enable)

                                        holder.dottwo.setImageResource(R.drawable.disable)

                                        holder.dotthree.setImageResource(R.drawable.enable)

                                        holder.dotfour.setImageResource(R.drawable.enable)

                                        holder.dotfive.setImageResource(R.drawable.enable)


                                    }


                                    2 -> {

                                        holder.dotone.setImageResource(R.drawable.enable)

                                        holder.dottwo.setImageResource(R.drawable.enable)

                                        holder.dotthree.setImageResource(R.drawable.disable)

                                        holder.dotfour.setImageResource(R.drawable.enable)

                                        holder.dotfive.setImageResource(R.drawable.enable)


                                    }

                                    3 -> {

                                        holder.dotone.setImageResource(R.drawable.enable)

                                        holder.dottwo.setImageResource(R.drawable.enable)

                                        holder.dotthree.setImageResource(R.drawable.enable)

                                        holder.dotfour.setImageResource(R.drawable.disable)

                                        holder.dotfive.setImageResource(R.drawable.enable)


                                    }

                                    4 -> {

                                        holder.dotone.setImageResource(R.drawable.enable)

                                        holder.dottwo.setImageResource(R.drawable.enable)

                                        holder.dotthree.setImageResource(R.drawable.enable)

                                        holder.dotfour.setImageResource(R.drawable.enable)

                                        holder.dotfive.setImageResource(R.drawable.disable)


                                    }


                                }


                            }

                            override fun onPageSelected(position: Int) {

                            }


                        }

                        )
                    }

                }


            }

        }

        if (holder is  ViewHolder_three){

            when (position){


                3 ->{

                    val my_list = inner_theme_list.filter { it.theme_name.contains("IT狗精品區")  }

                    if( my_list.size > 0) {

                        holder.muti_viewPager.adapter = My_ViewPage_Adapter(context,my_list)
                        holder.muti_viewPager.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
                            override fun onPageScrollStateChanged(state: Int) {

                            }

                            override fun onPageScrolled(
                                position: Int,
                                positionOffset: Float,
                                positionOffsetPixels: Int
                            ) {

                                when(position){

                                    0 ->{holder.dotone.setImageResource(R.drawable.disable)

                                        holder.dottwo.setImageResource(R.drawable.enable)

                                        holder.dotthree.setImageResource(R.drawable.enable)

                                        holder.dotfour.setImageResource(R.drawable.enable)

                                        holder.dotfive.setImageResource(R.drawable.enable)


                                    }


                                    1 ->{holder.dotone.setImageResource(R.drawable.enable)

                                        holder.dottwo.setImageResource(R.drawable.disable)

                                        holder.dotthree.setImageResource(R.drawable.enable)

                                        holder.dotfour.setImageResource(R.drawable.enable)

                                        holder.dotfive.setImageResource(R.drawable.enable)


                                    }


                                    2->{

                                        holder.dotone.setImageResource(R.drawable.enable)

                                        holder.dottwo.setImageResource(R.drawable.enable)

                                        holder.dotthree.setImageResource(R.drawable.disable)

                                        holder.dotfour.setImageResource(R.drawable.enable)

                                        holder.dotfive.setImageResource(R.drawable.enable)


                                    }

                                    3->{

                                        holder.dotone.setImageResource(R.drawable.enable)

                                        holder.dottwo.setImageResource(R.drawable.enable)

                                        holder.dotthree.setImageResource(R.drawable.enable)

                                        holder.dotfour.setImageResource(R.drawable.disable)

                                        holder.dotfive.setImageResource(R.drawable.enable)


                                    }

                                    4->{

                                        holder.dotone.setImageResource(R.drawable.enable)

                                        holder.dottwo.setImageResource(R.drawable.enable)

                                        holder.dotthree.setImageResource(R.drawable.enable)

                                        holder.dotfour.setImageResource(R.drawable.enable)

                                        holder.dotfive.setImageResource(R.drawable.disable)



                                    }


                                }


                            }

                            override fun onPageSelected(position: Int) {

                            }


                        })

                    }

                }



                6 ->{

                    val my_list = inner_theme_list.filter { it.theme_name == "主題｜設計模式" }

                    if( my_list.size > 0) {


                        holder.muti_viewPager.adapter = My_ViewPage_Adapter(context,my_list)
                        holder.muti_viewPager.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
                            override fun onPageScrollStateChanged(state: Int) {

                            }

                            override fun onPageScrolled(
                                position: Int,
                                positionOffset: Float,
                                positionOffsetPixels: Int
                            ) {

                                when(position){

                                    0 ->{holder.dotone.setImageResource(R.drawable.disable)

                                        holder.dottwo.setImageResource(R.drawable.enable)

                                        holder.dotthree.setImageResource(R.drawable.enable)

                                        holder.dotfour.setImageResource(R.drawable.enable)

                                        holder.dotfive.setImageResource(R.drawable.enable)


                                    }



                                    1 ->{holder.dotone.setImageResource(R.drawable.enable)

                                        holder.dottwo.setImageResource(R.drawable.disable)

                                        holder.dotthree.setImageResource(R.drawable.enable)

                                        holder.dotfour.setImageResource(R.drawable.enable)

                                        holder.dotfive.setImageResource(R.drawable.enable)


                                    }


                                    2->{

                                        holder.dotone.setImageResource(R.drawable.enable)

                                        holder.dottwo.setImageResource(R.drawable.enable)

                                        holder.dotthree.setImageResource(R.drawable.disable)

                                        holder.dotfour.setImageResource(R.drawable.enable)

                                        holder.dotfive.setImageResource(R.drawable.enable)


                                    }

                                    3->{

                                        holder.dotone.setImageResource(R.drawable.enable)

                                        holder.dottwo.setImageResource(R.drawable.enable)

                                        holder.dotthree.setImageResource(R.drawable.enable)

                                        holder.dotfour.setImageResource(R.drawable.disable)

                                        holder.dotfive.setImageResource(R.drawable.enable)


                                    }

                                    4->{

                                        holder.dotone.setImageResource(R.drawable.enable)

                                        holder.dottwo.setImageResource(R.drawable.enable)

                                        holder.dotthree.setImageResource(R.drawable.enable)

                                        holder.dotfour.setImageResource(R.drawable.enable)

                                        holder.dotfive.setImageResource(R.drawable.disable)



                                    }


                                }

                            }

                            override fun onPageSelected(position: Int) {

                            }


                        })


                    }

                }

                9 ->{

                    val my_list = inner_theme_list.filter { it.theme_name == "無瑕的程式碼超值合購" }

                    if( my_list.size > 0) {

                        holder.muti_viewPager.adapter = My_ViewPage_Adapter(context,my_list)
                        holder.muti_viewPager.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
                            override fun onPageScrollStateChanged(state: Int) {

                            }

                            override fun onPageScrolled(
                                position: Int,
                                positionOffset: Float,
                                positionOffsetPixels: Int
                            ) {

                                when(position){

                                    0 ->{holder.dotone.setImageResource(R.drawable.disable)

                                        holder.dottwo.setImageResource(R.drawable.enable)

                                        holder.dotthree.setImageResource(R.drawable.enable)

                                        holder.dotfour.setImageResource(R.drawable.enable)

                                        holder.dotfive.setImageResource(R.drawable.enable)


                                    }



                                    1 ->{holder.dotone.setImageResource(R.drawable.enable)

                                        holder.dottwo.setImageResource(R.drawable.disable)

                                        holder.dotthree.setImageResource(R.drawable.enable)

                                        holder.dotfour.setImageResource(R.drawable.enable)

                                        holder.dotfive.setImageResource(R.drawable.enable)


                                    }


                                    2->{

                                        holder.dotone.setImageResource(R.drawable.enable)

                                        holder.dottwo.setImageResource(R.drawable.enable)

                                        holder.dotthree.setImageResource(R.drawable.disable)

                                        holder.dotfour.setImageResource(R.drawable.enable)

                                        holder.dotfive.setImageResource(R.drawable.enable)


                                    }

                                    3->{

                                        holder.dotone.setImageResource(R.drawable.enable)

                                        holder.dottwo.setImageResource(R.drawable.enable)

                                        holder.dotthree.setImageResource(R.drawable.enable)

                                        holder.dotfour.setImageResource(R.drawable.disable)

                                        holder.dotfive.setImageResource(R.drawable.enable)


                                    }

                                    4->{

                                        holder.dotone.setImageResource(R.drawable.enable)

                                        holder.dottwo.setImageResource(R.drawable.enable)

                                        holder.dotthree.setImageResource(R.drawable.enable)

                                        holder.dotfour.setImageResource(R.drawable.enable)

                                        holder.dotfive.setImageResource(R.drawable.disable)



                                    }


                                }

                            }

                            override fun onPageSelected(position: Int) {

                            }


                        })

                    }




                }

            }}

        if (holder is ViewHolder_four){

            holder.bus_btn.setOnClickListener {

                val my_list = inner_category_list.filter { it.category_name == "資料科學" }

                val my_id = my_list[0].id

                val my_title = my_list[0].category_name

                val intent = Intent(context, Book_listActivity::class.java)

                intent.putExtra("id",my_id)

                intent.putExtra("title",my_title)

                context.startActivity(intent)

            }

            holder.lit_btn.setOnClickListener {

                val my_list = inner_category_list.filter { it.category_name == "人工智慧" }

                val my_id = my_list[0].id

                val my_title = my_list[0].category_name

                val intent = Intent(context, Book_listActivity::class.java)

                intent.putExtra("id",my_id)

                intent.putExtra("title",my_title)

                context.startActivity(intent)

            }

            holder.mor_btn.setOnClickListener {

                val intent = Intent(context, TotoalSortActivity::class.java)

                context.startActivity(intent)

            }

            holder.sci_btn.setOnClickListener {

                val my_list = inner_category_list.filter { it.category_name == "前端開發" }

                val my_id = my_list[0].id

                val my_title = my_list[0].category_name

                val intent = Intent(context,Book_listActivity::class.java)

                intent.putExtra("id",my_id)

                intent.putExtra("title",my_title)

                context.startActivity(intent)


            }

            holder.so_btn.setOnClickListener {

                val my_list = inner_category_list.filter { it.category_name == "職涯發展" }

                val my_id = my_list[0].id

                val my_title = my_list[0].category_name

                val intent = Intent(context,Book_listActivity::class.java)

                intent.putExtra("id",my_id)

                intent.putExtra("title",my_title)

                context.startActivity(intent)


            }

            holder.sty_btn.setOnClickListener {

                val my_list = inner_category_list.filter { it.category_name == "網頁設計" }

                val my_id = my_list[0].id

                val my_title = my_list[0].category_name

                val intent = Intent(context,Book_listActivity::class.java)

                intent.putExtra("id",my_id)

                intent.putExtra("title",my_title)

                context.startActivity(intent)


            }



        }


        if (holder is  ViewHolder_six){

            var pauseScroll = false

            holder.circleviewpage.adapter = My_ViewPageCircle_Adapter(context)

            holder.circleviewpage.currentItem = currentItem

            holder.circleviewpage.setOnTouchListener { v, event ->
                if(MotionEvent.ACTION_DOWN == event.action){
                    pauseScroll = true
                }else if(MotionEvent.ACTION_UP == event.action) {
                    pauseScroll = false
                }
                false
            }


            autoScrollRunnable?.let { it -> handler.removeCallbacks(it) }

            autoScrollRunnable  = object :Runnable{
                override fun run() {


                    if(!pauseScroll) {
                        val currentItem = holder.circleviewpage.currentItem
                        Log.d("ITEM", " scroll to ${(currentItem + 1) % 5}")
                        holder.circleviewpage.setCurrentItem((currentItem + 1) % 5, true)

                    }


                    handler.postDelayed(this,2500)

                }

            }
            handler.postDelayed(autoScrollRunnable,2500)

        }

        if (holder is  ViewHolder_seven){

            // 所有分類

            holder.TotoalSortbtn.setOnClickListener {

                val intent = Intent(context, TotoalSortActivity::class.java)

                context.startActivity(intent)

            }

            //主題推薦

            holder.FreeBookbtn.setOnClickListener {


                this_Onclick?.click(1)

            }

            //服務代訂英文書

            holder.Global_SellWellbtn.setOnClickListener {

                this_Onclick?.click(2)

            }

            //套件滿五千

            holder.LastBookbtn.setOnClickListener {

                this_Onclick?.click(3)


            }


        }

    }

    inner class ViewHolder_One(view: View): RecyclerView.ViewHolder(view){

        val single_viewPager = view.findViewById<ViewPager>(R.id.single_viewPager)

        val dotone = view.findViewById<ImageView>(R.id.dot_one)

        val dottwo =view.findViewById<ImageView>(R.id.dot_two)

        val dotthree =view.findViewById<ImageView>(R.id.dot_three)

        val dotfour = view.findViewById<ImageView>(R.id.dot_four)

        val dotfive = view.findViewById<ImageView>(R.id.dot_five)




    }

    inner class ViewHolder_Two(view: View): RecyclerView.ViewHolder(view)

    inner class ViewHolder_three(view: View): RecyclerView.ViewHolder(view){

        val muti_viewPager = view.findViewById<ViewPager>(R.id.muti_viewPager)

        val dotone = view.findViewById<ImageView>(R.id.muti_dot_one)

        val dottwo =view.findViewById<ImageView>(R.id.muti_dot_two)

        val dotthree =view.findViewById<ImageView>(R.id.muti_dot_three)

        val dotfour = view.findViewById<ImageView>(R.id.muti_dot_four)

        val dotfive = view.findViewById<ImageView>(R.id.muti_dot_five)



    }

    inner class ViewHolder_four(view: View): RecyclerView.ViewHolder(view){

        val sty_btn = view.findViewById<Button>(R.id.sty_btn)
        val bus_btn = view.findViewById<Button>(R.id.bus_btn)
        val so_btn = view.findViewById<Button>(R.id.so_btn)
        val sci_btn = view.findViewById<Button>(R.id.sci_btn)
        val mor_btn = view.findViewById<Button>(R.id.mor_btn)
        val lit_btn = view.findViewById<Button>(R.id.lit_btn)




    }

    inner class ViewHolder_five(view: View): RecyclerView.ViewHolder(view)

    inner class ViewHolder_six(view: View): RecyclerView.ViewHolder(view){

        val  circleviewpage =view.findViewById<ViewPager>(R.id.circleviewpage)

    }

    inner class ViewHolder_seven(view: View): RecyclerView.ViewHolder(view){

        val TotoalSortbtn = view.findViewById<ImageView>(R.id.TotoalSortbtn)

        val Global_SellWellbtn = view.findViewById<ImageView>(R.id.Global_SellWellbtn)

        val FreeBookbtn = view.findViewById<ImageView>(R.id.FreeBookbtn)

        val LastBookbtn  = view.findViewById<ImageView>(R.id.LastBookbtn)

    }


    fun update_themelist(my_list: MutableList<my_data_booktheme_list_two>){


        inner_theme_list = my_list

        notifyDataSetChanged()


    }

    fun update_category_list(my_list: MutableList<my_data_category>){

        inner_category_list = my_list

        notifyDataSetChanged()

    }



}