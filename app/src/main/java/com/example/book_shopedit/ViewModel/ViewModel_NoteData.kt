package com.example.book_shopedit.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.book_shopedit.Data.my_data_book_note
import com.example.book_shopedit.Model.ViewModel_Data.important_list
import com.example.book_shopedit.Model.ViewModel_Data.note_list
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



     fun set_note_list (list: MutableList<my_data_book_note>){

         note_list.value  = list

     }

     fun get_note_list():LiveData<MutableList<my_data_book_note>>{

       return note_list

     }


    fun set_important_note(list: MutableList<my_data_book_note>){


        important_list.value  = list


    }

    fun get_important_note():LiveData<MutableList<my_data_book_note>>{

        return important_list

    }


}