package com.example.book_shopedit.Recoder_Page.NoteFragment

import android.os.Bundle
import android.renderscript.Sampler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.book_shopedit.Adapter.SentencePageAdapter
import com.example.book_shopedit.Data.my_data_good_sentence

import com.example.book_shopedit.R
import com.example.book_shopedit.ViewModel.ViewModel_AddNote
import kotlinx.android.synthetic.main.alerdialog_page_addsentence.view.*
import kotlinx.android.synthetic.main.fragment_add_note__detail.*
import kotlinx.android.synthetic.main.fragment_add_note__detail.view.*
import java.lang.reflect.Array.set

/**
 * A simple [Fragment] subclass.
 */
class AddNote_DetailFragment : Fragment() {

    lateinit var rootView:View

    lateinit  var my_adapter : SentencePageAdapter

   private var my_list = mutableListOf<my_data_good_sentence>()

    private val  viewModel_addNote by lazy { ViewModelProviders.of(activity!!
    ).get(ViewModel_AddNote::class.java) }

   private var my_sentence = ""

   private var myPage = 0

       get()= field

       set(value) {

          val a = value.toString().toIntOrNull()

           if (a != null){

               field = value

           }
           else{

               Toast.makeText(context,"請輸入數字",Toast.LENGTH_SHORT).show()

           }

       }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_add_note__detail, container, false)

        my_adapter = SentencePageAdapter(context!!,my_list)

        //  心得 Edittext

        val experience_Listener = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                viewModel_addNote.set_experience(s.toString())

                    }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

              }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        }

        rootView.comment_insert.addTextChangedListener(experience_Listener)


        //  註記 Edittext

        val note_Listener = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                val viewmodel = viewModel_addNote

                Log.e("viewmodel2","$viewmodel")

                viewModel_addNote.set_note(s.toString())

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        }

        rootView.comment_insert_tag.addTextChangedListener(note_Listener)

        //  新增金句 button

        rootView.good_sentence_addim.setOnClickListener{

            val mDialogView = LayoutInflater.from(context!!).inflate(R.layout.alerdialog_page_addsentence, null)

            val mBuilder = AlertDialog.Builder(context!!)
                .setView(mDialogView)
                .show()

            mDialogView.my_sentence_insert.setOnClickListener {

                 my_sentence = mDialogView.my_add_sentence.text.toString()

                 myPage = mDialogView.my_add_page.text.toString().toInt()

                 my_list.add(my_data_good_sentence(myPage,my_sentence))

                 viewModel_addNote.set_positive_quotes(my_list)

                 my_adapter.update(my_list)

                 mBuilder.dismiss()

            }

        }
        //  金句 recyclerview

        rootView.sentence_recyclerview.apply {

            layoutManager = LinearLayoutManager(context)

            adapter = my_adapter
        }

        return rootView
    }

}
