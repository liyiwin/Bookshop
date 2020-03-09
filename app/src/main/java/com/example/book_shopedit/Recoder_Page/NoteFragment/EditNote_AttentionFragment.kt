package com.example.book_shopedit.Recoder_Page.NoteFragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.CompoundButton
import android.widget.RatingBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide

import com.example.book_shopedit.R
import com.example.book_shopedit.ViewModel.ViewModel_NoteDetails
import com.example.book_shopedit.sharedPreference.sharedPrefernce_EditData
import kotlinx.android.synthetic.main.alerdialog_page_addsentence.view.*
import kotlinx.android.synthetic.main.fragment_add_note__attention.view.*
import kotlinx.android.synthetic.main.fragment_edit_note__attention.*
import kotlinx.android.synthetic.main.fragment_edit_note__attention.view.*
import kotlinx.android.synthetic.main.fragment_edit_note__attention.view.language_insert


class EditNote_AttentionFragment : Fragment() {

    lateinit var rootView:View

    private val  viewModel_notedetails by lazy { ViewModelProviders.of(activity!!
    ).get(ViewModel_NoteDetails::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_edit_note__attention, container, false)

        // 資料輸入 get
        val pref = sharedPrefernce_EditData(context!!)

        val title_Obeserver = Observer<String>{

                    rootView.book_name_insert.setText(it)
                }

        viewModel_notedetails.get_title().observe(this,title_Obeserver)

        val press_Obeserver = Observer<String>{

                    rootView.press_insert.setText(it)

                }

        viewModel_notedetails.get_press().observe(this,press_Obeserver)

                val language_Obeserver =Observer<String>{

                    //
                    if (it == "English"){

                        language_insert.setSelection(0)
                    }

                    if (it =="中文"){

                        language_insert.setSelection(1)
                    }

                    if (it == "한국어"){

                        language_insert.setSelection(2)

                    }

                    if (it =="Italiano"){

                        language_insert.setSelection(3)
                    }

                    if (it == "Polski"){

                        language_insert.setSelection(4)

                    }

                }
                viewModel_notedetails.get_language().observe(this,language_Obeserver)

                val author_Obeserver =Observer<String>{

                    rootView.author_insert.setText(it)
                }

                viewModel_notedetails.get_author().observe(this,author_Obeserver)

                val status_Obeserver = Observer<String>{

                  if(it == "還未閱讀"){

                  rootView.status_insert.isChecked = false

                  }
                 if(it == "閱讀中"){

                   rootView.status_insert.isChecked = true

                   }
                }

                viewModel_notedetails.get_status().observe(this,status_Obeserver)


                val score_Obeserver =  Observer<String>{

                    rootView.rating_insert.rating  = it.toFloat()
                }

                viewModel_notedetails.get_score().observe(this,score_Obeserver)

                val my_uri_Obeserver = Observer<String>{

                    Glide.with(context!!)
                        .load(it)
                        .into(rootView.insertphoto)

                }

                viewModel_notedetails.get_uri().observe(this,my_uri_Obeserver)



         //  之後資料輸出

          //  書名 Edittext

        rootView.book_name_insert.setOnClickListener {

            viewModel_notedetails.get_title().removeObserver(title_Obeserver)

            val  listener_bookname = object :TextWatcher{
                override fun afterTextChanged(s: Editable?) {

                   viewModel_notedetails.set_title(s.toString())


                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

            }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }
            }


            rootView.book_name_insert.addTextChangedListener(listener_bookname)
        }

        //  出版社 Edittext





        val listener_publish  = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel_notedetails.get_press().removeObserver(press_Obeserver)

                viewModel_notedetails.set_press(s.toString())

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        }

        rootView.press_insert.addTextChangedListener(listener_publish)

        //  作者 Edittext





        val listener_author = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                viewModel_notedetails.get_author().removeObserver(author_Obeserver)

                viewModel_notedetails.set_author(s.toString())

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }


        }

        rootView.author_insert.addTextChangedListener(listener_author)


        // 選擇語言的spinner

        rootView.language_insert.onItemSelectedListener = object:  AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val languge_array = resources.getStringArray(R.array.languge_array)

                val languge = languge_array[position]

                viewModel_notedetails.get_language().removeObserver(language_Obeserver)

                viewModel_notedetails.set_language(languge)
            }


        }

        // 是否閱讀中的 checkBox


        rootView.status_insert.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

                viewModel_notedetails.get_status().removeObserver(status_Obeserver)

                if (isChecked == true){

                    viewModel_notedetails.set_status("1")

                }

                else{

                    viewModel_notedetails.set_status("0")

                }


            }
        })


        // 評分的 RatingBar


        rootView.rating_insert.onRatingBarChangeListener = object : RatingBar.OnRatingBarChangeListener{

            override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, fromUser: Boolean) {

                viewModel_notedetails.get_score().removeObserver(score_Obeserver)

                val myscore = rating.toInt().toString()

                viewModel_notedetails.set_score(myscore)

            }

        }



        return rootView
    }


}
