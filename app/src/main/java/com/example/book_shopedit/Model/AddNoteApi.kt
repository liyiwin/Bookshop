package com.example.book_shopedit.Model

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.example.book_shopedit.Data.my_data_good_sentence
import com.example.book_shopedit.ViewModel.ViewModel_AddNote
import okhttp3.*
import org.json.JSONObject
import java.io.File
import java.io.IOException

private var OkHttpClient = okhttp3.OkHttpClient().newBuilder().build()

fun add_bookdata(api_token:String,title:String,press:String,language:String,author:String,status:String,score:String,my_path:String,viewModel:ViewModel_AddNote,activity: Activity) {
    val file = File(my_path)

    if(file != null
        && file.exists()
        && title != ""
        && press != ""
        && language != ""
        && author != ""
        && status != ""
        && score != ""

    ) {
    val url = "http://104.199.148.167/api/reading/information"

    val requestBody = RequestBody.create(MediaType.parse("image/*"), file)
    val body = MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart("api_token", api_token)
        .addFormDataPart("title", title)
        .addFormDataPart("press", press)
        .addFormDataPart("language", language)
        .addFormDataPart("author", author)
        .addFormDataPart("status", status)
        .addFormDataPart("score", score)
        .addFormDataPart("image", file.name, requestBody)
        .build()

    val request = Request.Builder().url(url).post(body).build()
    val call = OkHttpClient.newCall(request)
    call.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {

            val myjson = response.body()?.string()!!.trim()

            val code = response.code()

            if (code == 200) {

                val jsonObject = JSONObject(myjson)



                val object_infoContents =  jsonObject.getJSONObject( "data about InfoContents")

                val reading_info_id = object_infoContents.getInt("reading_info_id")

                val reading_content_id =  object_infoContents.getInt("reading_content_id")


                activity.runOnUiThread{
                viewModel.set_reading_info_id(reading_info_id.toString())

                viewModel.set_reading_content_id(reading_content_id.toString())

                viewModel.set_information_responsecode("200")}

            }

        }
    })
}

}



fun add_experience(api_token:String,reading_info_id:String,experience:String,note:String,viewModel:ViewModel_AddNote,activity: Activity){

    val url2 = "http://104.199.148.167/api/reading/contents"
    val body2 = FormBody.Builder()
        .add("api_token", api_token)
        .add("reading_info_id", reading_info_id)
        .add("experience", experience)
        .add("note", note)
        .build()
    val request2 = Request.Builder().url(url2).post(body2).build()
    val call2 = OkHttpClient.newCall(request2)
    call2.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {


            val myjson = response.body()?.string()!!.trim()

            val code = response.code()

            if (code == 200) {
                activity.runOnUiThread{
                viewModel.set_content_responsecode("200")}

            }
        }
    })

}


fun add_contents (api_token:String, my_sentencelist:MutableList<my_data_good_sentence>, reading_content_id:String, reading_info_id:String,viewModel:ViewModel_AddNote,activity: Activity){

    for (i in 0 until my_sentencelist.size) {

        val my_quotes = my_sentencelist[i].sentence

        val my_page = my_sentencelist[i].page

        val url3 = "http://104.199.148.167/api/reading/quotes"
        val body3 = FormBody.Builder()
            .add("api_token", api_token)
            .add("positive_quotes", my_quotes)
            .add("pages", "$my_page")
            .add("reading_content_id",reading_content_id )
            .add("reading_info_id",reading_info_id ).build()
        val request3 = Request.Builder().url(url3).post(body3).build()
        val call3 = OkHttpClient.newCall(request3)
        call3.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {

                val myjson = response.body()?.string()!!.trim()

                val code = response.code()

                if (code == 200) {

                   while (i == my_sentencelist.size -1){
                       activity.runOnUiThread{
                       viewModel.set_quotes_responsecode("200")}

                   }


                }
            }
        })


    }


}