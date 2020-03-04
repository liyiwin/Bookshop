package com.example.book_shopedit

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.book_shopedit.Person_Page.LoginActivity
import com.example.book_shopedit.ViewModel.ViewModel_Login
import com.example.book_shopedit.sharedPreference.sharedPreference_login
import kotlinx.android.synthetic.main.fragment_person_.*
import kotlinx.android.synthetic.main.fragment_person_.view.*


class Person_Fragment : Fragment() {

    lateinit var rootView:View

    lateinit var viewModel_login :ViewModel_Login

    var a ="0"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val pref = sharedPreference_login(context!!)

        rootView = inflater.inflate(R.layout.fragment_person_, container, false)

               viewModel_login = ViewModel_Login(context!!)

        viewModel_login.set_name()

        viewModel_login.get_name().observe(this,Observer<String>{

        rootView.user_name.text = it

         when (it){
             "未登入" ->{

                 rootView.Login_Intent_Button.text = "登入"

                 rootView.Login_Intent_Button.setOnClickListener {

                     val intent =Intent(context,LoginActivity::class.java)

                     startActivity(intent)

                 }

             }

              else   ->{

                  rootView.Login_Intent_Button.text = "登出"

                  rootView.Login_Intent_Button.setOnClickListener {

                      pref.delete()

                      viewModel_login.set_name()

                  }



              }


         }

        })



        return rootView

    }



    override fun onResume() {
        super.onResume()

        viewModel_login.set_name()



    }






}
