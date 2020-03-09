package com.example.book_shopedit.Recoder_Page

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.book_shopedit.Model.edit_content
import com.example.book_shopedit.Model.getBookdetails
import com.example.book_shopedit.Model.getBooknote
import com.example.book_shopedit.R
import com.example.book_shopedit.Recoder_Page.NoteFragment.EditNote_AttentionFragment
import com.example.book_shopedit.Recoder_Page.NoteFragment.EditNote_DetailFragment
import com.example.book_shopedit.ViewModel.ViewModel_NoteDetails
import com.example.book_shopedit.sharedPreference.sharedPreference_login
import kotlinx.android.synthetic.main.activity_edit_note.*

class EditNoteActivity : AppCompatActivity() {

    private val  viewModel_notedetails by lazy { ViewModelProviders.of(this
    ).get(ViewModel_NoteDetails::class.java) }

    lateinit var adapter:Edit_Note_Adapter

    var title = "String"

    var public = "String"

    var language = "String"

    var author =  "String"

    var score = "String"

    var experience =  "String"

    var note =  "String"




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        val pref = sharedPreference_login(this)

        val apitoken = pref.get_apitoken()

        val read_info_id = intent.getStringExtra("read_info_id")

        val read_content = intent.getStringExtra("read_content")

        setupView()

        getBookdetails(read_info_id!!,apitoken!!,this,viewModel_notedetails)

        getBooknote(read_content!!,apitoken,this,viewModel_notedetails)

        edit_note_toolbar.inflateMenu(R.menu.menu_editnote)

        edit_note_toolbar.setOnMenuItemClickListener {

            when(it.itemId){

                R.id.note_complete ->{

                    edit_content(this@EditNoteActivity,apitoken,read_info_id,title,public,language,author,score,experience,note,viewModel_notedetails)

                }

            }
            true

        }



        val edit_content_Observer = Observer<String>{

            if(it == "200"){

                val intent = Intent(this@EditNoteActivity,NotesActivity::class.java)

                startActivity(intent)

                finish()

            }
        }

        viewModel_notedetails.get_editContent_responsecode().observe(this,edit_content_Observer)

        // 觀察這些數據的變化 要傳出用

        val title_Observer =  Observer<String> {

            title = it
        }

        viewModel_notedetails.get_title().observe(this,title_Observer)

        val public_Observer =  Observer<String> {

            public = it

        }

        viewModel_notedetails.get_press().observe(this,public_Observer )

        val language_Observer =  Observer<String> {

            language = it

        }

        viewModel_notedetails.get_language().observe(this,language_Observer )

        val author_Observer =  Observer<String> {

            author = it

        }

        viewModel_notedetails.get_author().observe(this,author_Observer)

        val score_Observer =  Observer<String> {

            score = it

        }

        viewModel_notedetails.get_score().observe(this,score_Observer)

        val experience_Observer =  Observer<String> {

            experience = it

        }

        viewModel_notedetails.get_experience().observe(this,experience_Observer)

        val note_Observer =  Observer<String> {

            note = it
        }

        viewModel_notedetails.get_note().observe(this,note_Observer)

    }




    private fun setupView() {

        adapter = Edit_Note_Adapter(supportFragmentManager)

        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        edit_note_container.adapter = adapter

        tabLayout3.setupWithViewPager(edit_note_container)
    }

    inner class Edit_Note_Adapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager){
        override fun getItem(position: Int): Fragment {

            when(position){

                0 ->return   EditNote_AttentionFragment()

                else->return EditNote_DetailFragment()


            }

        }

        override fun getCount(): Int = 2

        override fun getPageTitle(position: Int): CharSequence {
            when(position){
                0 -> return "基本資料"
                else-> return "心得"

            }
        }

    }

}
