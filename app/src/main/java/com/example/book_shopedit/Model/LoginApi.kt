package com.example.book_shopedit.Model

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.book_shopedit.Person_Page.LoginActivity
import com.example.book_shopedit.ViewModel.ViewModel_Login
import com.example.book_shopedit.sharedPreference.sharedPreference_login
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


private var OkHttpClient = okhttp3.OkHttpClient().newBuilder().build()



fun loginApi(password:String,email:String,pref:sharedPreference_login){

    val url = "http://104.199.148.167/api/login"
    val body = FormBody.Builder().add("password",password).add("email",email).build()
    val request = Request.Builder().url(url).post(body) .build()
    val call = OkHttpClient .newCall(request)
    call.enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {

            val myjson = response.body()?.string()!!.trim()
            val code = response.code()

            Log.e("code","$code")

            if (code == 200) {

                val jsonObject = JSONObject(myjson)

                val data= jsonObject.getJSONObject("data")

                val my_name = data.getString("name")

                val my_email = data.getString("email")

                val my_apitoken =  data.getString("api_token")

                pref.save_name(my_name)
                pref.save_apitoken(my_apitoken)

            }

        }

    })


}