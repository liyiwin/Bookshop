package com.example.book_shopedit.Recoder_Page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.book_shopedit.Adapter.NoteDeleteAdapter
import com.example.book_shopedit.Data.my_data_book_note
import com.example.book_shopedit.Model.deleteNote
import com.example.book_shopedit.Model.setNote
import com.example.book_shopedit.R
import com.example.book_shopedit.ViewModel.ViewModel_DeleteNote
import com.example.book_shopedit.ViewModel.ViewModel_NoteData
import com.example.book_shopedit.sharedPreference.sharedPreference_login
import kotlinx.android.synthetic.main.activity_delete_note.*

class DeleteNoteActivity : AppCompatActivity() {
    private val viewModel_noteData by lazy { ViewModelProviders.of(this
    ).get(ViewModel_NoteData::class.java) }

    private val viewModel_noteDelete by lazy { ViewModelProviders.of(this
    ).get(ViewModel_DeleteNote::class.java) }



    private var Datalist = mutableListOf<my_data_book_note>()

    lateinit var note_adapter :  NoteDeleteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_note)

        note_adapter = NoteDeleteAdapter(this,Datalist)

        deletenote_toolbar.setNavigationOnClickListener {

            val intent = Intent(this,NotesActivity::class.java)

            startActivity(intent)

            finish()

        }

      val pref = sharedPreference_login(this)

      val  token = pref.get_apitoken()!!


        setNote(viewModel_noteData,token,this)

        val list_Obeserver  = Observer<MutableList<my_data_book_note>>{

            Datalist = it

            note_adapter.update(Datalist)

        }

        viewModel_noteData.get_note_list().observe(this,list_Obeserver)


        delete_list_recyclerview.apply {

            adapter = note_adapter

            layoutManager = LinearLayoutManager(this@DeleteNoteActivity)

        }


        val deletecode_Obesever = Observer<String>{


            if (it == "200"){

                Toast.makeText(this,"刪除成功",Toast.LENGTH_SHORT).show()

            }

        }


        viewModel_noteDelete.get_deleteNote_responsecode().observe(this,deletecode_Obesever)





         note_adapter.my_note_id(object:NoteDeleteAdapter.Delete_id{

             override fun this_delete(ReadingInfo_id: Int, position: Int) {

                 Datalist.removeAt(position)

                 viewModel_noteData.set_note_list(Datalist)

                 deleteNote("$ReadingInfo_id",token,viewModel_noteDelete,this@DeleteNoteActivity)

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
