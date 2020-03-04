package com.example.book_shopedit.Person_Page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.book_shopedit.ForgetPasswordActivity
import com.example.book_shopedit.Model.ViewModel_Data
import com.example.book_shopedit.Model.loginApi
import com.example.book_shopedit.R
import com.example.book_shopedit.ViewModel.ViewModel_Login
import com.example.book_shopedit.sharedPreference.sharedPreference_login
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

  private  var my_mail = "0"

  private  var my_password = "0"

  lateinit var viewModel_login : ViewModel_Login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val pref = sharedPreference_login(this)

        Login_Button.setOnClickListener {

            mydata_input()

            loginApi(my_password,my_mail,pref)

        }

        to_forget_password_page.setOnClickListener {

            val intent = Intent (this@LoginActivity, ForgetPasswordActivity::class.java)

            startActivity(intent)

        }

        Registered_Intent_Button.setOnClickListener {

            val intent = Intent (this@LoginActivity,RegisteredActivity::class.java)

            startActivity(intent)
        }
    }


    fun mydata_input(){

        my_mail = "liy721511@gmail.com"
        my_password= "1212"

    }

}
