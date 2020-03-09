package com.example.book_shopedit.Model

import android.app.Activity
import android.content.Context
import com.example.book_shopedit.Data.my_data_good_sentence_edit
import com.example.book_shopedit.ViewModel.ViewModel_NoteDetails
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

private var OkHttpClient = okhttp3.OkHttpClient().newBuilder().build()

fun edit_content (activity:Activity,apitoken:String,readingInfo:String,my_edit_title:String,my_edit_press:String,my_edit_booklanguage:String,my_edit_author:String,my_edit_bookscore:String,my_edit_experience:String,my_edit_note:String,viewModel: ViewModel_NoteDetails){


    val url = "http://104.199.148.167/api/reading/$readingInfo"

    val mediaType = MediaType.parse("application/x-www-form-urlencoded")

    val body = RequestBody.create(mediaType,"title=$my_edit_title&press=$my_edit_press&language=$my_edit_booklanguage&author=$my_edit_author&score=$my_edit_bookscore&experience=$my_edit_experience&note=$my_edit_note")

    val request = Request.Builder()
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .addHeader("Authorization", "Bearer $apitoken")
        .url(url)
        .put(body)
        .build()
    val call = OkHttpClient .newCall(request)
    call.enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {



        }

        override fun onResponse(call: Call, response: Response) {

            val myjson = response.body()?.string()!!.trim()

            val code = response.code()

            if(code == 200){

                activity.runOnUiThread{
                    viewModel.set_editContent_responsecode("200")
                }



            }
        }
    })


}

fun edit_quote (my_list:MutableList<my_data_good_sentence_edit>,position: Int,context:Context,activity: Activity,info_content_id:String,apitoken:String,my_sentence:String,my_page:String,viewModel: ViewModel_NoteDetails){

    val url1 = "http://104.199.148.167/api/reading/quotes/$info_content_id"

    val mediaType = MediaType.parse("application/x-www-form-urlencoded")

    val body = RequestBody.create(mediaType, "positive_quotes=$my_sentence&pages=$my_page")

    val request1 = Request.Builder()
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .addHeader("Authorization", "Bearer $apitoken")
        .url(url1)
        .put(body)
        .build()
    val call1 = OkHttpClient .newCall(request1)
    call1.enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {

            val myjson = response.body()?.string()!!.trim()

            val code = response.code()

            if(code == 200) {

                val jsonObject = JSONObject(myjson)

                val data = jsonObject.getJSONObject("data")

                val id = data.getInt("id")

                val positive_quotes = data.getString("positive_quotes")

                val pages = data.getString("pages").toInt()

                my_list.set(position, my_data_good_sentence_edit(pages,positive_quotes,id))

                activity.runOnUiThread{

                    viewModel.set_positive_quotes_edit(my_list)

                    viewModel.set_editQuote_responsecode("200")

                }

            }

        }
    })
}


fun delete_quote (my_list:MutableList<my_data_good_sentence_edit>,position: Int,info_content_id:String,apitoken:String,viewModel: ViewModel_NoteDetails,activity: Activity){

    val url1 = " http://104.199.148.167/api/reading/quotes/$info_content_id"
    val body =  FormBody.Builder().add("api_token", apitoken).build();
    val request1 = Request.Builder().url(url1).delete(body).build()

    val call1 = OkHttpClient .newCall(request1)

    call1.enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {

            val code = response.code()

            if (code == 200){

                my_list.removeAt(position)

                activity.runOnUiThread{

                    viewModel.set_positive_quotes_edit(my_list)

                    viewModel.set_deleteQuote_responsecode("200")

                }
            }
        }
    })
}