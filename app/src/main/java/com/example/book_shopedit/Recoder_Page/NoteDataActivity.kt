package com.example.book_shopedit.Recoder_Page

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProviders
import com.example.book_shopedit.Model.getBookdetails
import com.example.book_shopedit.Model.getBooknote
import com.example.book_shopedit.R
import com.example.book_shopedit.Recoder_Page.NoteFragment.NoteData_AttentionFragment
import com.example.book_shopedit.Recoder_Page.NoteFragment.NoteData_DetailFragment
import com.example.book_shopedit.ViewModel.ViewModel_AddNote
import com.example.book_shopedit.ViewModel.ViewModel_NoteDetails
import com.example.book_shopedit.sharedPreference.sharedPreference_login
import kotlinx.android.synthetic.main.activity_note_data.*

class NoteDataActivity : AppCompatActivity() {

    private val  viewModel_notedetails by lazy { ViewModelProviders.of(this
    ).get(ViewModel_NoteDetails::class.java) }

    lateinit var adapter:Add_Note_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_data)
        val pref = sharedPreference_login(this)
        val apitoken = pref.get_apitoken()

        setupView()

        val read_info_id = intent.getStringExtra("read_info_id")

        val read_content = intent.getStringExtra("read_content")

        viewModel_notedetails.set_reading_info_id(read_info_id!!)

        viewModel_notedetails.set_reading_content_id(read_content!!)

       getBookdetails(read_info_id,apitoken!!,this,viewModel_notedetails)

       getBooknote(read_content,apitoken,this,viewModel_notedetails)


        note_data_toolbar.inflateMenu(R.menu.menu_notedetail)
        note_data_toolbar.setOnMenuItemClickListener {

            when( it.itemId){

                R.id.note_edit->{

                    val intent = Intent(this@NoteDataActivity,EditNoteActivity::class.java)

                    startActivity(intent)

                }

            }

            true

        }
    }

    private fun setupView() {

        adapter = Add_Note_Adapter(supportFragmentManager)

        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        note_data_container.adapter = adapter

        note_data_tabLayout.setupWithViewPager(note_data_container)
    }

    inner class Add_Note_Adapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager){
        override fun getItem(position: Int): Fragment {

            when(position){

                0 ->return   NoteData_AttentionFragment()

                else->return NoteData_DetailFragment()

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
