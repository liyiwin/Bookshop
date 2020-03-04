package com.example.book_shopedit.sharedPreference

import android.content.Context

class sharedPreference_login (context: Context)  {

    private val pref = context.getSharedPreferences("Total_login", Context.MODE_PRIVATE)

    val editor    = pref.edit()


    fun save_name(name: String) {

        editor.putString("name", name).apply()

    }


    //xml 輸出

    fun get_name(): String? {
        return pref.getString("name", "未登入")

    }

    fun save_email(email: String) {

        editor.putString("email", email).apply()

    }


    //xml 輸出

    fun get_email(): String? {
        return pref.getString("email", "0")

    }


    fun save_apitoken(apitoken: String) {

        editor.putString("apitoken", apitoken).apply()

    }


    //xml 輸出

    fun get_apitoken(): String? {
        return pref.getString("apitoken", "0")

    }



    fun delete() {

        editor.clear().commit()
    }

}