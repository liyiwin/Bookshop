package com.example.book_shopedit.Model

import android.app.Activity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

private var OkHttpClient = okhttp3.OkHttpClient().newBuilder().build()

fun get_book_detail(activity: Activity,id:String){

    val url = "http://104.199.148.167/api/books/$id"
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
                val my_image =  data.getJSONObject(0).getString("image")
                val my_author =   data.getJSONObject(0).getString("author")
                val my_introduce =data.getJSONObject(0).getString("intro")
                val my_title =  data.getJSONObject(0).getString("title")
                val my_price =data.getJSONObject(0).getInt("price")
                val my_id = data.getJSONObject(0).getInt("id")

                activity.runOnUiThread(object :Runnable{
                    override fun run() {



                    }

                })

            }

        }
    })

}


fun get_book_publish(activity: Activity,my_id:String){

    val url2 = "http://104.199.148.167/api/publisher/books/$my_id "
    val request2 = Request.Builder().url(url2).build()
    val call2 = OkHttpClient .newCall(request2)
    call2.enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {

            val myjson2 = response.body()?.string()!!.trim()

            val code2 = response.code()

            if (code2 == 200){

                val jsonObject2 = JSONObject(myjson2)

                val data = jsonObject2.getJSONArray("data")
                val my_author =  data.getJSONObject(0).getString("author")
                val publication_date =   data.getJSONObject(0).getString("publication_date")
                val press =data.getJSONObject(0).getString("press")
                val language =data.getJSONObject(0).getString("language")
                val ISBN =data.getJSONObject(0).getString("ISBN")

                activity.runOnUiThread(object :Runnable{
                    override fun run() {

                    }

                })

            }


        }
    })


}