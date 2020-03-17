package com.example.book_shopedit.Model

import android.app.Activity
import androidx.lifecycle.ViewModel
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