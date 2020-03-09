package com.example.book_shopedit.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.book_shopedit.Data.my_data_good_sentence
import com.example.book_shopedit.Data.my_data_good_sentence_edit

// EditNote NoteData 使用這個 viewModel

class ViewModel_NoteDetails(app: Application): AndroidViewModel(app) {


    private var title = MutableLiveData<String>()
    private var press = MutableLiveData<String>()
    private var language = MutableLiveData<String>()
    private var author = MutableLiveData<String>()
    private var status = MutableLiveData<String>()
    private var score = MutableLiveData<String>()
    private var uri = MutableLiveData<String>()
    private var reading_info_id = MutableLiveData<String>()
    private var reading_content_id = MutableLiveData<String>()
    private var experience = MutableLiveData<String>()
    private var note = MutableLiveData<String>()
    private var positive_quotes = MutableLiveData<MutableList<my_data_good_sentence>>()
    private var positive_quotes_edit = MutableLiveData<MutableList<my_data_good_sentence_edit>>()
    private var bookdetails_responsecode = MutableLiveData<String>()
    private var booknote_responsecode = MutableLiveData<String>()

    //edit_page
    private var editcontent_responsecode = MutableLiveData<String>()
    private var editQuote_responsecode = MutableLiveData<String>()
    private var deleteQuote_responsecode = MutableLiveData<String>()


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


    fun set_experience (my_experience:String){

        experience .value = my_experience

    }

    fun get_experience():MutableLiveData<String>{

        return experience

    }

    fun set_uri (my_uri:String){


        uri .value = my_uri

    }

    fun get_uri():MutableLiveData<String>{

        return uri

    }


    fun set_note(my_note:String){

        note.value = my_note

    }

    fun get_note():MutableLiveData<String>{

        return note

    }


    fun set_positive_quotes_edit(my_positive_quotes:MutableList<my_data_good_sentence_edit>){

        positive_quotes_edit.value = my_positive_quotes

    }

    fun get_positive_quotes_edit():MutableLiveData<MutableList<my_data_good_sentence_edit>>{

        return positive_quotes_edit

    }


    fun set_positive_quotes(my_positive_quotes:MutableList<my_data_good_sentence>){

        positive_quotes.value = my_positive_quotes

    }

    fun get_positive_quotes():MutableLiveData<MutableList<my_data_good_sentence>>{

        return positive_quotes

    }


   fun set_editContent_responsecode(my_editContent_responsecode:String){

       editcontent_responsecode.value = my_editContent_responsecode

    }

    fun get_editContent_responsecode():MutableLiveData<String>{

        return editcontent_responsecode

    }



    fun set_editQuote_responsecode(my_editQuote_responsecode:String){

        editQuote_responsecode.value = my_editQuote_responsecode

    }

    fun get_editQuote_responsecode():MutableLiveData<String>{

        return editQuote_responsecode

    }



    fun set_deleteQuote_responsecode(my_deleteQuote_responsecode:String){

        deleteQuote_responsecode.value = my_deleteQuote_responsecode

    }

    fun get_deleteQuote_responsecode():MutableLiveData<String>{

        return deleteQuote_responsecode

    }

}