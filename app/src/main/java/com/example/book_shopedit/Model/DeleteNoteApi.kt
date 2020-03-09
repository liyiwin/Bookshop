package com.example.book_shopedit.Model

import android.app.Activity
import com.example.book_shopedit.Adapter.NoteDeleteAdapter
import com.example.book_shopedit.ViewModel.ViewModel_DeleteNote
import okhttp3.*
import java.io.IOException

private var OkHttpClient = okhttp3.OkHttpClient().newBuilder().build()

fun deleteNote(readingInfo_id:String,mytoken:String,viewModel:ViewModel_DeleteNote,activity: Activity){


    val url1 = "http://104.199.148.167/api/reading/record/$readingInfo_id"
    val body =  FormBody.Builder().add("api_token", mytoken).build();
    val request1 = Request.Builder().url(url1).delete(body).build()

    val call1 = OkHttpClient .newCall(request1)

    call1.enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {

            val code = response.code()

            activity.runOnUiThread {

                viewModel.set_deleteNote_responsecode("$code")


            }

        }
    })





}