package com.example.book_shopedit.Recoder_Page

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.book_shopedit.Data.my_data_good_sentence
import com.example.book_shopedit.Model.add_bookdata
import com.example.book_shopedit.Model.add_quotes
import com.example.book_shopedit.Model.add_experience
import com.example.book_shopedit.R
import com.example.book_shopedit.Recoder_Page.NoteFragment.AddNote_AttentionFragment
import com.example.book_shopedit.Recoder_Page.NoteFragment.AddNote_DetailFragment
import com.example.book_shopedit.ViewModel.ViewModel_AddNote
import com.example.book_shopedit.sharedPreference.sharedPreference_login
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

    lateinit var adapter:Add_Note_Adapter

    private val  viewModel_addNote by lazy { ViewModelProviders.of(this
    ).get(ViewModel_AddNote::class.java) }

    private var apitoken  = ""

    private var title  = ""

    private var press  = ""

    private var language  = ""

    private var author  = ""

    private var status  = ""

    private var score  = ""

    private var my_path  = ""

    private var reading_info_id = ""

    private var reading_content_id = ""

    private var experience = ""

    private var note = ""

    private var my_sentencelist = mutableListOf<my_data_good_sentence>()

    private var  information_responsecode = "0"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)


        val pref = sharedPreference_login(this)

        apitoken = pref.get_apitoken()!!

        val title_Obeserver = Observer<String>{

            title = it
        }

        viewModel_addNote.get_title().observe(this,title_Obeserver)

        val press_Obeserver =Observer<String>{

            press = it

        }

        viewModel_addNote.get_press().observe(this,press_Obeserver)

        val language_Obeserver =Observer<String>{

            language =it
        }
        viewModel_addNote.get_language().observe(this,language_Obeserver)

        val author_Obeserver =Observer<String>{

            author = it
        }

        viewModel_addNote.get_author().observe(this,author_Obeserver)

        val status_Obeserver =Observer<String>{

            status = it
        }

        viewModel_addNote.get_status().observe(this,status_Obeserver)


        val score_Obeserver =  Observer<String>{

            score = it
        }

        viewModel_addNote.get_score().observe(this,score_Obeserver)

        val my_path_Obeserver = Observer<String>{

            my_path = it
        }

        viewModel_addNote.get_path().observe(this,my_path_Obeserver)

        add_note_toolbar.inflateMenu(R.menu.menu_addnote)

        setupView()

        val reading_info_id_Observer = Observer<String>{

            reading_info_id = it

        }

        viewModel_addNote.get_reading_info_id().observe(this,reading_info_id_Observer)

        val reading_content_id_Observer= Observer<String>{

            reading_content_id = it

        }

        viewModel_addNote.get_reading_content_id().observe(this,reading_content_id_Observer)

        val my_sentencelist_Observer= Observer<MutableList<my_data_good_sentence>>{

            my_sentencelist.addAll(it)

        }

        viewModel_addNote.get_positive_quotes().observe(this,my_sentencelist_Observer)



        val information_responsecode_Obeserver = Observer<String>{

            if (it == "200"){

                if (reading_content_id != "" && reading_info_id != ""){

                    add_experience(apitoken,reading_info_id,experience,note,viewModel_addNote,this)


                }


            }


        }

        viewModel_addNote.get_information_responsecode().observe(this,information_responsecode_Obeserver)


        val content_responsecode_Obeserver = Observer<String> {

            if (it == "200"){

            add_quotes (apitoken,my_sentencelist ,reading_content_id,reading_info_id,viewModel_addNote,this)

            }



        }


        viewModel_addNote.get_content_responsecode().observe(this,content_responsecode_Obeserver)

        val quotes_responsecode_Obeserver = Observer<String> {

          if (it == "200"){


          }

        }

        viewModel_addNote.get_quotes_responsecode().observe(this,quotes_responsecode_Obeserver)


        val note__Obeserver = Observer<String> {

            note = it

        }

        viewModel_addNote.get_note().observe(this,note__Obeserver)

        val experiment_Obeserver = Observer<String> {

            experience = it

        }

        viewModel_addNote.get_experience().observe(this,experiment_Obeserver)


        val positive_quotes_Observer =  Observer<MutableList<my_data_good_sentence>> {

            my_sentencelist = it

        }


        viewModel_addNote.get_positive_quotes().observe(this,positive_quotes_Observer)

         add_note_toolbar.setOnMenuItemClickListener {

            when (it.itemId) {

                R.id.note_save -> {

                    add_bookdata(apitoken,title,press,language,author,status,score,my_path,viewModel_addNote,this)

                }
            }

          true

       }

    }

    private fun setupView() {

        adapter = Add_Note_Adapter(supportFragmentManager)

        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        add_note_container.adapter = adapter

        tabLayout.setupWithViewPager(add_note_container)
    }


    inner class Add_Note_Adapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager){
        override fun getItem(position: Int): Fragment {

            when(position){

                0 ->return   AddNote_AttentionFragment()

                else -> return AddNote_DetailFragment()

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
