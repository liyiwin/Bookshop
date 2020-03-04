package com.example.book_shopedit.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.book_shopedit.Data.my_data_good_sentence

class ViewModel_AddNote(app: Application): AndroidViewModel(app) {

    private var title = MutableLiveData<String>()
    private var press = MutableLiveData<String>()
    private var language = MutableLiveData<String>()
    private var author = MutableLiveData<String>()
    private var status = MutableLiveData<String>()
    private var score = MutableLiveData<String>()
    private var path = MutableLiveData<String>()
    private var reading_info_id = MutableLiveData<String>()
    private var reading_content_id = MutableLiveData<String>()
    private var experience = MutableLiveData<String>()
    private var note = MutableLiveData<String>()
    private var positive_quotes = MutableLiveData<MutableList<my_data_good_sentence>>()
    private var information_responsecode = MutableLiveData<String>()
    private var content_responsecode = MutableLiveData<String>()
    private var quotes_responsecode = MutableLiveData<String>()



    fun set_information_responsecode(my_information_responsecode:String){

        information_responsecode.value = my_information_responsecode

    }


    fun get_information_responsecode():MutableLiveData<String>{

        return information_responsecode

    }



    fun set_content_responsecode(my_content_responsecode:String){

        content_responsecode.value = my_content_responsecode

    }


    fun get_content_responsecode():MutableLiveData<String>{

        return content_responsecode

    }


    fun set_quotes_responsecode(my_quotes_responsecode:String){

        quotes_responsecode.value = my_quotes_responsecode

    }


    fun get_quotes_responsecode():MutableLiveData<String>{

        return quotes_responsecode

    }


    fun set_title (my_title:String){

        title .value = my_title

    }

    fun get_title():MutableLiveData<String>{

        return title

    }

    fun set_press (my_press:String){

        press .value = my_press

    }

    fun get_press():MutableLiveData<String>{

        return press

    }


    fun set_language (my_language:String){

        language .value = my_language

    }

    fun get_language():MutableLiveData<String>{

        return language

    }


    fun set_author (my_author:String){

        author .value = my_author

    }

    fun get_author():MutableLiveData<String>{

        return author

    }

    fun set_status (my_status:String){

        status .value = my_status

    }

    fun get_status():MutableLiveData<String>{

        return status

    }

    fun set_score (my_score:String){

        score .value = my_score

    }

    fun get_score():MutableLiveData<String>{

        return score

    }

    fun set_reading_info_id (my_reading_info_id:String){

        reading_info_id .value = my_reading_info_id

    }

    fun get_reading_info_id():MutableLiveData<String>{

        return reading_info_id

    }

    fun set_reading_content_id (my_reading_content_id:String){

        reading_content_id .value = my_reading_content_id

    }

    fun get_reading_content_id():MutableLiveData<String>{

        return reading_content_id

    }

    fun set_experience (my_experience:String){

        experience .value = my_experience

    }

    fun get_experience():MutableLiveData<String>{

        return experience

    }

    fun set_path (my_path:String){

        path .value = my_path

    }

    fun get_path():MutableLiveData<String>{

        return path

    }


    fun set_note(my_note:String){

        note.value = my_note

    }

    fun get_note():MutableLiveData<String>{

        return note

    }


    fun set_positive_quotes(my_positive_quotes:MutableList<my_data_good_sentence>){

        positive_quotes.value = my_positive_quotes

    }

    fun get_positive_quotes():MutableLiveData<MutableList<my_data_good_sentence>>{

        return positive_quotes

    }




}