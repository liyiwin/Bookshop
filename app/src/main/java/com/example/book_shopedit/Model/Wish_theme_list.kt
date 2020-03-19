package com.example.book_shopedit.Model

import android.app.Activity
import com.example.book_shopedit.Data.my_data_book_theme
import com.example.book_shopedit.Data.my_data_booktheme_list
import com.example.book_shopedit.ViewModel.ViewModel_Total_list
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

private var OkHttpClient = okhttp3.OkHttpClient().newBuilder().build()

fun filter_themewishlist(viewModel: ViewModel_Total_list, theme:String, mytoken:String, activity: Activity){

    val my_like_list = mutableListOf<Int>()
    val url4 = "http://104.199.148.167/api/wishlists"
    val request4 = Request.Builder().url(url4).addHeader("Authorization", "Bearer $mytoken").build()
    val call4 = OkHttpClient .newCall(request4)
    call4.enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {

            val myjson2 = response.body()?.string()!!.trim()

            val code2 = response.code()

            if (code2 == 200) {

                val jsonObject2 = JSONObject(myjson2)

                val message = jsonObject2.getString("message")

                if (message != "I am sorry, there are currently no books in the wishlists"){

                    val data2 = jsonObject2.getJSONArray("data")

                    for (i in 0 until data2.length()) {

                        val my_object = data2.getJSONObject(i)
                        val book_id = my_object.getInt("book_id")
                        my_like_list.add(book_id)
                    }

                    this_sort_themelist(viewModel, activity, theme,my_like_list)
                }

            }


        }
    })
}








fun add_into_themewishlist(viewModel:ViewModel_Total_list,id_add:String,mytoken:String,activity: Activity,list:MutableList<my_data_booktheme_list>){

    val url2 = "http://104.199.148.167/api/books/addToWishList"
    val body2 = FormBody.Builder().add("book_id",id_add).add("api_token",mytoken).build()
    val request2 = Request.Builder().url(url2).post(body2) .build()
    val call2 = OkHttpClient .newCall(request2)
    call2.enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {

            val myjson = response.body()?.string()!!.trim()

            val code = response.code()

            if (code == 200){

                val id = id_add.toInt()

                list.map{ if (it.book_id == id)  {it.is_in_like = true}  }

                activity.runOnUiThread{

                    viewModel.set_total_theme_list(list)

                }


            }


        }
    })


}


fun remove_from_themewishlist(viewModel:ViewModel_Total_list,id_delete:String,mytoken:String,activity: Activity,list:MutableList<my_data_booktheme_list>) {

    val url3 = "http://104.199.148.167/api/books/deleteWishBook/$id_delete"

    val body3 = FormBody.Builder().add("book_id", id_delete).add("api_token", mytoken).build()

    val request3 = Request.Builder().url(url3).delete(body3).build()

    val call3 = OkHttpClient.newCall(request3)

    call3.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {

            val code = response.code()

            if (code == 200) {

                val id = id_delete.toInt()

                list.map{ if (it.book_id == id){it.is_in_like = false}  }

                activity.runOnUiThread {

                    viewModel.set_total_theme_list(list)


                }

            }

        }

    })}











