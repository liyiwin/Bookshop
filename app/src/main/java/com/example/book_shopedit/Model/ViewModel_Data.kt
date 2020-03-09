package com.example.book_shopedit.Model

import androidx.lifecycle.MutableLiveData
import com.example.book_shopedit.Data.my_data_book_note
import com.example.book_shopedit.ViewModel.ViewModel_Login

object ViewModel_Data{


    val note_list = MutableLiveData<MutableList<my_data_book_note>>()

    val important_list = MutableLiveData<MutableList<my_data_book_note>>()



}