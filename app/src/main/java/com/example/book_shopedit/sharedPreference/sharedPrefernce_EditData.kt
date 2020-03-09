package com.example.book_shopedit.sharedPreference

import android.content.Context
import com.example.book_shopedit.Data.my_data_good_sentence

class sharedPrefernce_EditData (context: Context)  {

    private val pref = context.getSharedPreferences("EditData", Context.MODE_PRIVATE)

    val editor    = pref.edit()


    fun save_bookname(book_name: String) {

        editor.putString("book_name", book_name).apply()
    }

    fun get_bookname(): String? {

        return pref.getString("book_name", "0")

    }

    fun save_publish(publish: String) {

        editor.putString("publish", publish).apply()

    }


    fun get_publish(): String? {
        return pref.getString("publish", "0")

    }

    fun save_languge(languge: String) {

        editor.putString("languge", languge).apply()

    }


    fun get_languge(): String? {
        return pref.getString("languge", "0")

    }


    fun save_author(author: String) {

        editor.putString("author", author).apply()

    }


    fun get_author(): String? {
        return pref.getString("author", "0")

    }


    fun save_status(status: String) {

        editor.putString("status", status).apply()

    }

    fun get_status():String? {
        return pref.getString("status", "0")

    }

    fun save_score(score: String) {

        editor.putString("score", score).apply()

    }

    fun get_score(): String? {
        return pref.getString("score", "0")

    }


    fun save_experience(experience: String) {

        editor.putString("experience",experience).apply()

    }

    fun get_experience(): String? {
        return pref.getString("experience","0")

    }

    fun save_note(note:String) {

        editor.putString("note",note).apply()

    }

    fun get_note(): String? {
        return pref.getString("note","0")

    }

    fun save_uri(uri:String){

        editor.putString("uri",uri)

    }

   fun  get_uri(): String? {

       return pref.getString("uri","0")

   }





}