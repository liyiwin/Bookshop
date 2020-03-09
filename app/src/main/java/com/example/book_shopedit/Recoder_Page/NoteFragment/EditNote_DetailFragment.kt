package com.example.book_shopedit.Recoder_Page.NoteFragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.book_shopedit.Adapter.NoteEditAdapter
import com.example.book_shopedit.Data.my_data_good_sentence_edit
import com.example.book_shopedit.Model.delete_quote
import com.example.book_shopedit.Model.edit_quote

import com.example.book_shopedit.R
import com.example.book_shopedit.ViewModel.ViewModel_NoteDetails
import com.example.book_shopedit.sharedPreference.sharedPreference_login
import kotlinx.android.synthetic.main.alerdialog_page_editsentence.view.*
import kotlinx.android.synthetic.main.fragment_edit_note__detail.view.*

/**
 * A simple [Fragment] subclass.
 */
class EditNote_DetailFragment : Fragment() {

    lateinit var rootView:View

    private val  viewModel_notedetails by lazy { ViewModelProviders.of(activity!!
    ).get(ViewModel_NoteDetails::class.java) }

    var list = mutableListOf<my_data_good_sentence_edit>()

    lateinit var my_adapter : NoteEditAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_edit_note__detail, container, false)

        my_adapter = NoteEditAdapter(context!!,list)

        rootView.editsentence_recyclerview.apply {

            adapter = my_adapter

            layoutManager = LinearLayoutManager(context)

        }

        // 資料輸入 get

        val experience_Observer = Observer<String>{

            rootView.comment_edit.setText(it)
        }

       viewModel_notedetails.get_experience().observe(this,experience_Observer)

       val note_Observer = Observer<String>{

           rootView.tag_edit.setText(it)

       }

       viewModel_notedetails.get_note().observe(this,note_Observer)



        val quote_Observer = Observer<MutableList<my_data_good_sentence_edit>> {

          if (list.size == 0){

              list = it

          }

          my_adapter.update(it)

        }

        viewModel_notedetails.get_positive_quotes_edit().observe(this,quote_Observer)



        val editquote_responsecode_Observer = Observer<String> {

            if (it == "200"){

                Toast.makeText(context,"修改成功",Toast.LENGTH_SHORT).show()

            }
        }

        viewModel_notedetails.get_editQuote_responsecode().observe(this,editquote_responsecode_Observer)


        val deletequote_responsecode_Observer = Observer<String> {

            if (it == "200"){

                Toast.makeText(context,"刪除成功",Toast.LENGTH_SHORT).show()

            }

        }

        viewModel_notedetails.get_deleteQuote_responsecode().observe(this,deletequote_responsecode_Observer)



         //之後輸出


        //  心得 Edittext

        viewModel_notedetails.get_experience().removeObserver(experience_Observer)

        val experience_Listener = object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {

                viewModel_notedetails.set_experience(s.toString())

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        }


        rootView.comment_edit.addTextChangedListener(experience_Listener)



        //  註記 Edittext

        viewModel_notedetails.get_note().removeObserver(note_Observer)

        val note_Listener = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                viewModel_notedetails.set_note(s.toString())

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        }

        rootView.tag_edit.addTextChangedListener(note_Listener)



        my_adapter.my_edit(object:NoteEditAdapter.Edit{
            override fun this_edit(info_content_id: Int, position: Int) {

                val mDialogView =LayoutInflater.from(context!!).inflate(R.layout.alerdialog_page_editsentence, null)

                val mBuilder = AlertDialog.Builder(context!!)
                    .setView(mDialogView)
                    .show()

                val pref = sharedPreference_login(context!!)

                val apitoken = pref.get_apitoken()

                // list 在這裡做變更 （set）

                mDialogView.my_editsentence_insert.setOnClickListener{

                    val my_sentence = mDialogView.my_edit_sentence.text.toString()

                    val my_page = mDialogView.my_edit_page.text.toString()

                    edit_quote(list,position,context!!,activity!!,info_content_id.toString(),apitoken!!,my_sentence,my_page,viewModel_notedetails)

                    mBuilder.dismiss()
                }
            }
        })

        my_adapter.my_delete(object:NoteEditAdapter.Delete{
            override fun this_Delete(info_content_id: Int, position: Int) {

                val pref = sharedPreference_login(context!!)

                val apitoken = pref.get_apitoken()

                delete_quote (list,position,info_content_id.toString(),apitoken!!,viewModel_notedetails,activity!!)
            }
        })

        return rootView
    }

}
