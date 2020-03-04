package com.example.book_shopedit.ViewModel

import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.example.book_shopedit.Person_Page.LoginActivity
import com.example.book_shopedit.sharedPreference.sharedPreference_login
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ViewModel_Login(context: Context):ViewModel(){


    val pref = sharedPreference_login(context)

    val api_token = MutableLiveData<String>()

    val name = MutableLiveData<String>()

    val number = MutableLiveData<String>()


    fun set_name(){

        name.value =  pref.get_name()

    }

    fun get_token():LiveData<String>{

        return api_token

    }


    fun get_name():LiveData<String>{

        return name

    }




}