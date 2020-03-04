package com.example.book_shopedit.Recoder_Page.NoteFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide

import com.example.book_shopedit.R
import com.example.book_shopedit.ViewModel.ViewModel_NoteDetails
import kotlinx.android.synthetic.main.fragment_note_data__attention.*
import kotlinx.android.synthetic.main.fragment_note_data__attention.view.*

/**
 * A simple [Fragment] subclass.
 */
class NoteData_AttentionFragment : Fragment() {

    private val  viewModel_notedetails by lazy { ViewModelProviders.of(activity!!
    ).get(ViewModel_NoteDetails::class.java) }

    lateinit var rootView:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_note_data__attention, container, false)

        val title_Obeserver = Observer<String>{

            rootView.my_book_name.text = it
        }

        viewModel_notedetails.get_title().observe(this,title_Obeserver)

        val press_Obeserver =Observer<String>{

            rootView.my_book_press.text = it

        }

        viewModel_notedetails.get_press().observe(this,press_Obeserver)

        val language_Obeserver =Observer<String>{

            rootView.my_book_languge.text =  it
        }
        viewModel_notedetails.get_language().observe(this,language_Obeserver)

        val author_Obeserver =Observer<String>{

            rootView.my_book_author.text = it
        }

        viewModel_notedetails.get_author().observe(this,author_Obeserver)

        val status_Obeserver =Observer<String>{

            rootView.my_book_status.text = it
        }

        viewModel_notedetails.get_status().observe(this,status_Obeserver)


        val score_Obeserver =  Observer<String>{

            rootView.my_book_ratingBar.rating  = it.toFloat()
        }

        viewModel_notedetails.get_score().observe(this,score_Obeserver)

        val my_uri_Obeserver = Observer<String>{

          Glide.with(context!!)
          .load(it)
          .into(rootView.my_book_image)

        }

        viewModel_notedetails.get_uri().observe(this,my_uri_Obeserver)

        return  rootView
    }

}
