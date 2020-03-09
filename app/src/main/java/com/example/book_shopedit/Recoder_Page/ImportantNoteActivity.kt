package com.example.book_shopedit.Recoder_Page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.book_shopedit.Adapter.NoteImportantAdapter
import com.example.book_shopedit.Data.my_data_book_note
import com.example.book_shopedit.Model.setNote
import com.example.book_shopedit.Model.set_important_note
import com.example.book_shopedit.R
import com.example.book_shopedit.ViewModel.ViewModel_NoteData
import com.example.book_shopedit.sharedPreference.sharedPreference_login
import kotlinx.android.synthetic.main.activity_important_note.*

class ImportantNoteActivity : AppCompatActivity() {

    private val viewModel_noteData by lazy { ViewModelProviders.of(this
    ).get(ViewModel_NoteData::class.java) }

    var my_list = mutableListOf<my_data_book_note>()

    private val my_adapter = NoteImportantAdapter(this@ImportantNoteActivity,my_list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_important_note)

        val pref = sharedPreference_login(this)

        val token = pref.get_apitoken()!!

        setNote(viewModel_noteData,token,this)

        importantnote_toolbar.setNavigationOnClickListener {

            val intent = Intent(this,NotesActivity::class.java)

            startActivity(intent)

            finish()

        }


        val list_Observer = Observer<MutableList<my_data_book_note>>{

             my_list = it.filter { it.is_mark == 1 }.toMutableList()

            my_adapter.update(my_list)

        }


        viewModel_noteData.get_note_list().observe(this,list_Observer)

        important_list_recyclerview.apply {

            adapter = my_adapter

            layoutManager = LinearLayoutManager(context)

        }

        my_adapter.my_note_id(object:NoteImportantAdapter.Important_id{

            override fun this_important(ReadingInfo_id: Int, position: Int) {

                my_list.removeAt(position)

                my_adapter.update(my_list)

                val readingInfo_id = ReadingInfo_id.toString()

                set_important_note(readingInfo_id,token)
            }
        })

    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            val intent = Intent(this,NotesActivity::class.java)

            startActivity(intent)

            finish()

            return false
        }

        return super.onKeyDown(keyCode, event)
    }
}
