package com.example.book_shopedit.Model

import android.app.Activity
import android.app.DownloadManager
import com.example.book_shopedit.Data.my_data_book_note
import com.example.book_shopedit.ViewModel.ViewModel_NoteData
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

private var OkHttpClient = okhttp3.OkHttpClient().newBuilder().build()

fun setNote(viewModel:ViewModel_NoteData,mytoken:String,activity: Activity){

    val url = "http://104.199.148.167/api/reading/records"
    val request = Request.Builder().url(url).addHeader("Authorization", "Bearer $mytoken").build()
    val call = OkHttpClient .newCall(request)
    call.enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {

            val myjson = response.body()?.string()!!.trim()

            val code = response.code()


            if(code == 200){

                val jsonObject = JSONObject(myjson)

                val list = mutableListOf<my_data_book_note>()

                var list2 = mutableListOf<my_data_book_note>()

                val my_data = jsonObject.getJSONArray("data")

                for(i in 0 until my_data.length()){

                    val  my_object = my_data.getJSONObject(i)
                    val  reading_content_id = my_object.getInt("reading_content_id")
                    val  reading_info_id =  my_object.getInt("reading_info_id")
                    val  user_id = my_object.getInt("user_id")
                    val  title = my_object.getString("title")
                    val  created_at =  my_object.getString("created_at")
                    val  updated_at =  my_object.getString("updated_at")
                    val  image = my_object.getString("image")
                    val  is_mark = my_object.getInt("is_mark")

                    list.add(my_data_book_note(reading_content_id,reading_info_id,user_id,title,created_at,updated_at,image,is_mark))

                }

                list2 = filter_note(list)

                activity.runOnUiThread {

                    viewModel.set_note_list(list2)

                }



            }

        }

    })
}


fun set_important_note(readingInfo_id:String, mytoken:String){

    val url2 = "http://104.199.148.167/api/reading/important/$readingInfo_id"
    val body = FormBody.Builder().add("api_token","$mytoken").build()
    val request2 = Request.Builder().url(url2).post(body) .build()
    val call2 = OkHttpClient .newCall(request2)
    call2.enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {

            val myjson = response.body()?.string()!!.trim()

            val code = response.code()


            if(code == 200){


            }


        }
    })

}


