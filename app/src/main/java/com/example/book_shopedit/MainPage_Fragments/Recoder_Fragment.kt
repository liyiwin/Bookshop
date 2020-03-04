package com.example.book_shopedit.MainPage_Fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.book_shopedit.R
import com.example.book_shopedit.Recoder_Page.NotesActivity
import kotlinx.android.synthetic.main.fragment_recoder_.*
import kotlinx.android.synthetic.main.fragment_recoder_.view.*

/**
 * A simple [Fragment] subclass.
 */
class Recoder_Fragment : Fragment() {

    lateinit var rootview: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootview =  inflater.inflate(R.layout.fragment_recoder_, container, false)

        rootview.my_read_notesdata.setOnClickListener {

            val intent = Intent(context, NotesActivity::class.java)
            startActivity(intent)

        }



        return rootview


    }


}
