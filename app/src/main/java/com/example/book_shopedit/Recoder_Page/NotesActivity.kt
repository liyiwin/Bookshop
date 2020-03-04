package com.example.book_shopedit.Recoder_Page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.book_shopedit.Adapter.Note_Adapter
import com.example.book_shopedit.Data.my_data_book_note
import com.example.book_shopedit.R
import com.example.book_shopedit.ViewModel.ViewModel_Login
import com.example.book_shopedit.ViewModel.ViewModel_NoteData
import com.example.book_shopedit.sharedPreference.sharedPreference_login
import kotlinx.android.synthetic.main.activity_notes.*

class NotesActivity : AppCompatActivity() {

    private val viewModel_noteData by lazy { ViewModelProviders.of(this
    ).get(ViewModel_NoteData::class.java) }

    private var token:String = "0"

    private var note_list_Observer = Observer<MutableList<my_data_book_note>> {  }

    private var list = mutableListOf<my_data_book_note>()

     var my_adapter  = Note_Adapter(this@NotesActivity,list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        val pref = sharedPreference_login(this)

        token = pref.get_apitoken()!!

        notes_toolbar.inflateMenu(R.menu.menu_note)

        notes_toolbar.setOnMenuItemClickListener {

            when( it.itemId){

                R.id.add_my_new_note->{

                    val intent = Intent(this@NotesActivity , AddNoteActivity::class.java)

                    startActivity(intent)

                }


                R.id.delete_my_note->{

                    val intent = Intent(this@NotesActivity , DeleteNoteActivity::class.java)

                    startActivity(intent)

                }


                R.id.my_important_note->{

                    val intent = Intent(this@NotesActivity , ImportantNoteActivity::class.java)

                    startActivity(intent)

                }

            }
            true

        }

        setmylist()



    }


    fun setmylist(){

         viewModel_noteData.set_note_list(token)

         note_list_Observer = Observer<MutableList<my_data_book_note>> {

           note_recyclerview.apply {

              layoutManager = LinearLayoutManager(this@NotesActivity)

               my_adapter = Note_Adapter(this@NotesActivity,it)

               adapter =  my_adapter

               my_adapter.my_click(object:Note_Adapter.Click{

                   override fun this_click(read_info: Int, read_content: Int) {

                       val intent = Intent (this@NotesActivity,NoteDataActivity::class.java)

                       intent.putExtra("read_info_id","$read_info")

                       intent.putExtra("read_content","$read_content")

                       startActivity(intent)

                   }
               })

             }

        }

        viewModel_noteData.get_note_list().observe(this,note_list_Observer)

    }


}
