package com.example.book_shopedit.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.book_shopedit.Data.my_data_book_list
import com.example.book_shopedit.Data.my_data_booktheme_list

class ViewModel_Total_list(app: Application): AndroidViewModel(app) {


    private var total_list = MutableLiveData<MutableList<my_data_book_list>>()

    fun set_total_list(list:MutableList<my_data_book_list>){

        total_list.value = list

    }


    fun get_total_list():MutableLiveData<MutableList<my_data_book_list>>{

      return  total_list

    }


    private var total_theme_list = MutableLiveData<MutableList<my_data_booktheme_list>>()

    fun set_total_theme_list(list:MutableList<my_data_booktheme_list>){

        total_theme_list.value = list

    }


    fun get_total_theme_list():MutableLiveData<MutableList<my_data_booktheme_list>>{

        return  total_theme_list

    }





}