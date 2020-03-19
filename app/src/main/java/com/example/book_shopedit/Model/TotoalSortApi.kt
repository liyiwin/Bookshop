package com.example.book_shopedit.Model

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.example.book_shopedit.Data.my_data_book_theme
import com.example.book_shopedit.Data.my_data_booktheme_list_two
import com.example.book_shopedit.Data.my_data_category
import com.example.book_shopedit.ViewModel.ViewModel_TotoalSort
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

private var OkHttpClient = okhttp3.OkHttpClient().newBuilder().build()

fun add_bookcategory (viewModel: ViewModel_TotoalSort,activity: Activity) {

    val category = mutableListOf<my_data_category>()

    val url = "http://104.199.148.167/api/list/categories"
    val request = Request.Builder().url(url).build()
    val call = OkHttpClient .newCall(request)
    call.enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {

            val myjson = response.body()?.string()!!.trim()

            val code = response.code()

            if (code == 200){

                val jsonObject = JSONObject(myjson)

                val data = jsonObject.getJSONArray("data")

                for(i in 0 until data.length()){

                    val my_object = data.getJSONObject(i)

                    val id = my_object.getInt("id")

                    val category_name = my_object.getString("category_name")

                    val  is_category = my_object.getInt("category_id")

                    category.add(my_data_category(id, category_name, is_category))

                }

                activity.runOnUiThread(object :Runnable{
                    override fun run() {

                     viewModel.set_my_category(category)

                    }
                })

            }

        }
    })

}


fun add_booktheme(viewModel: ViewModel_TotoalSort,activity: Activity){

    val my_theme = mutableListOf<my_data_book_theme>()

    val url = "http://104.199.148.167/api/list/themes"
    val request = Request.Builder().url(url).build()
    val call = OkHttpClient .newCall(request)
    call.enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {

            val myjson = response.body()?.string()!!.trim()

            val code = response.code()

            if(code == 200){

                val jsonObject = JSONObject(myjson)

                val data = jsonObject.getJSONArray("data")

                for(i in 0 until data.length()){

                    val myobject = data.getJSONObject(i)
                    val id = myobject.getInt("id")
                    val theme_name = myobject.getString("theme_name")
                    val is_theme = myobject.getInt("theme_id")
                    my_theme.add(my_data_book_theme(id,theme_name,is_theme))

                    activity.runOnUiThread {

                        viewModel.set_my_theme(my_theme)

                    }

                }
            }
        }
    })
}

fun get_theme_books(my_id:Int,my_title:String,my_themelist:MutableList<my_data_booktheme_list_two>){


    val url2 = "http://104.199.148.167/api/themes/books/$my_id"
    val request2 = Request.Builder().url(url2).build()
    val call2 = OkHttpClient .newCall(request2)
    call2.enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {

            val myjson2 = response.body()?.string()!!.trim()

            val code2 = response.code()

            if (code2 == 200) {

                val jsonObject2 = JSONObject(myjson2)

                val data2 = jsonObject2.getJSONArray("data")

                for(i in 0 until data2.length()){

                    val myobject = data2.getJSONObject(i)

                    val title = myobject.getString("title")
                    val author = myobject.getString("author")
                    val translator = myobject.getString("translator")
                    val press = myobject.getString("press")
                    val intro = myobject.getString("intro")
                    val image = myobject.getString("image")
                    val comment_score = myobject.getInt("comment_score")
                    val book_id = myobject.getInt("book_id")
                    val theme_id = myobject.getInt("theme_id")

                    my_themelist.add(
                        my_data_booktheme_list_two(title,author,translator, press, intro, image, comment_score, book_id, theme_id,my_title

                        )
                    )
                }

            }
        }
    })


}







