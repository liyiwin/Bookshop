package com.example.book_shopedit.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class ViewModel_DeleteNote(app: Application): AndroidViewModel(app) {

    val deleteNote_responsecode =  MutableLiveData<String>()

    fun set_deleteNote_responsecode(code:String){

        deleteNote_responsecode.value = code

    }

    fun get_deleteNote_responsecode():MutableLiveData<String>{

        return deleteNote_responsecode

    }



}