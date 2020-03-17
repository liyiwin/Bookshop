package com.example.book_shopedit.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.book_shopedit.Data.my_data_category

class ViewModel_TotoalSort(app: Application): AndroidViewModel(app) {

    private var my_category = MutableLiveData<MutableList<my_data_category>>()

    fun set_my_category(this_category:MutableList<my_data_category>){

        my_category.value = this_category

    }


    fun get_my_category():MutableLiveData<MutableList<my_data_category>>{

       return my_category
    }

}