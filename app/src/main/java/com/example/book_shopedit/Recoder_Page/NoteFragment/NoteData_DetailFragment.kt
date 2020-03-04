package com.example.book_shopedit.Recoder_Page.NoteFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.book_shopedit.Adapter.NoteDataAdapter
import com.example.book_shopedit.Data.my_data_good_sentence

import com.example.book_shopedit.R
import com.example.book_shopedit.ViewModel.ViewModel_NoteDetails
import kotlinx.android.synthetic.main.fragment_note_data__detail.view.*

/**
 * A simple [Fragment] subclass.
 */
class NoteData_DetailFragment : Fragment() {

    private val  viewModel_notedetails by lazy { ViewModelProviders.of(activity!!
    ).get(ViewModel_NoteDetails::class.java) }

    lateinit var rootView:View

    private var list =  mutableListOf<my_data_good_sentence>()

    lateinit  var my_adapter : NoteDataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_note_data__detail, container, false)

        my_adapter = NoteDataAdapter (context!!,list)

        val experience_Observer = Observer<String>{

            rootView.comment_data.text = it

        }

        viewModel_notedetails.get_experience().observe(this,experience_Observer)

        val note_Observer =  Observer<String> {

            rootView.comment_tag_data.text = it
        }

        viewModel_notedetails.get_note().observe(this,note_Observer)


      val  positive_quotes_Observer =  Observer<MutableList<my_data_good_sentence>> {

          my_adapter.update(it)

      }

        viewModel_notedetails.get_positive_quotes().observe(this,positive_quotes_Observer)

        rootView.sentence_recyclerview.apply {

            layoutManager = LinearLayoutManager(context)

            adapter = my_adapter

        }

        return  rootView


    }

}
