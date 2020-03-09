package com.example.book_shopedit.Model

import android.app.Activity
import android.content.SharedPreferences
import com.example.book_shopedit.Data.my_data_good_sentence
import com.example.book_shopedit.Data.my_data_good_sentence_edit
import com.example.book_shopedit.ViewModel.ViewModel_NoteDetails
import com.example.book_shopedit.sharedPreference.sharedPrefernce_EditData
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException


// 閱讀基本資料Api
private var OkHttpClient = okhttp3.OkHttpClient().newBuilder().build()

fun getBookdetails(read_info_id:String,apitoken:String,activity: Activity,viewModel:ViewModel_NoteDetails){

    val url = "http://104.199.148.167/api/reading/information/$read_info_id"
    val request = Request.Builder().url(url).addHeader("Authorization", "Bearer $apitoken").build()
    val call = OkHttpClient .newCall(request)
    call.enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {

            val myjson = response.body()?.string()!!.trim()

            val code = response.code()

            if (code == 200) {

                val jsonObject = JSONObject(myjson)

                val data =  jsonObject.getJSONObject("data")

                val title  = data.getString("title")
                val press  = data.getString("press")
                val language  = data.getString("language")
                val author  = data.getString("author")
                val status  = data.getInt("status")
                val image  = data.getString("image")
                val score  = data.getInt("score")


                activity.runOnUiThread {

                    viewModel.set_title(title)

                    viewModel.set_press(press)

                    viewModel.set_language(language)

                    viewModel.set_author(author)

                    if(status == 0){

                       viewModel.set_status("還未閱讀")
                    }

                    else{

                        viewModel.set_status("閱讀中")

                    }

                    viewModel.set_score(score.toString())
                   viewModel.set_uri(image)


                }

            }

        }
    })

}

// 閱讀其他資料Api


fun getBooknote(read_content_id:String,apitoken: String,activity: Activity,viewModel:ViewModel_NoteDetails){

    val my_list = mutableListOf<my_data_good_sentence>()

    val my_list_edit = mutableListOf<my_data_good_sentence_edit>()

    val url = "http://104.199.148.167/api/reading/contents/$read_content_id"
    val request = Request.Builder().url(url).addHeader("Authorization", "Bearer $apitoken").build()
    val call = OkHttpClient .newCall(request)
    call.enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {

            val myjson = response.body()?.string()!!.trim()


            val code = response.code()

            if (code == 200) {

                val jsonObject = JSONObject(myjson)

                val data = jsonObject.getJSONArray("data")

                for(i in 0 until data.length()){

                    val my_object = data.getJSONObject(i)

                    val positive_quotes = my_object.getString("positive_quotes")

                    val pages = my_object.getInt("pages")

                    val id = my_object.getInt("id")

                    my_list.add(my_data_good_sentence(pages,positive_quotes))

                    my_list_edit.add(my_data_good_sentence_edit(pages,positive_quotes,id))

                }

              val  experience = data.getJSONObject(0).getString("experience")

              val  note = data.getJSONObject(0).getString("note")

                activity.runOnUiThread{

                    // NoteDataPage

                    viewModel.set_positive_quotes(my_list)

                    //EditNotePage

                    viewModel.set_positive_quotes_edit(my_list_edit)

                    viewModel.set_experience(experience)

                    viewModel.set_note(note)

                }

            }

        }
    })

}


