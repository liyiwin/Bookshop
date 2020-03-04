package com.example.book_shopedit.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.book_shopedit.Data.my_data_book_note
import com.example.book_shopedit.Model.filter_note
import kotlinx.android.synthetic.main.activity_notes.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.lang.reflect.Array


class ViewModel_NoteData(app:Application): AndroidViewModel(app) {

    private var OkHttpClient = okhttp3.OkHttpClient().newBuilder().build()

    val note_list = MutableLiveData<MutableList<my_data_book_note>>()

     fun set_note_list (mytoken:String){

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

                     note_list.postValue(list2)

                 }

             }

         })



     }

     fun get_note_list():LiveData<MutableList<my_data_book_note>>{

       return note_list

     }




}