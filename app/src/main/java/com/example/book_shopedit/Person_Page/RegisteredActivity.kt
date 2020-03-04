package com.example.book_shopedit.Person_Page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.book_shopedit.R
import kotlinx.android.synthetic.main.activity_registered.*

class RegisteredActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registered)

        Registered_Button.setOnClickListener {

            val mDialogView = LayoutInflater.from(this@RegisteredActivity).inflate(R.layout.alerdialog_page, null)

            val mBuilder = AlertDialog.Builder(this@RegisteredActivity)
                .setView(mDialogView)
                .show()


        }


    }
}
