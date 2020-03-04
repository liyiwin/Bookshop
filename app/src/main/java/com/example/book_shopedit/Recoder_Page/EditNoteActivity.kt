package com.example.book_shopedit.Recoder_Page

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.book_shopedit.R
import com.example.book_shopedit.Recoder_Page.NoteFragment.EditNote_AttentionFragment
import com.example.book_shopedit.Recoder_Page.NoteFragment.EditNote_DetailFragment
import kotlinx.android.synthetic.main.activity_edit_note.*

class EditNoteActivity : AppCompatActivity() {

    lateinit var adapter:Edit_Note_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)
        edit_note_toolbar.inflateMenu(R.menu.menu_editnote)

        setupView()
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
