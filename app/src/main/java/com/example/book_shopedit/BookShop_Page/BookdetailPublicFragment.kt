package com.example.book_shopedit.BookShop_Page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.book_shopedit.R
import com.example.book_shopedit.ViewModel.ViewModel_BookDetail
import kotlinx.android.synthetic.main.fragment_bookdetail_public.*

/**
 * A simple [Fragment] subclass.
 */
class BookdetailPublicFragment : Fragment() {

    private val viewModel by lazy {

        ViewModelProviders.of(activity!!).get(ViewModel_BookDetail::class.java)

    }

    lateinit var rootView:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_bookdetail_public, container, false)

        val author_Observer = Observer<String>{

            book_detail_publicauthor.text = "作者：$it"

        }

        viewModel.get_author().observe(this,author_Observer)

        val date_Observer= Observer<String>{

            book_detail_publicdate.text = "出版日期：$it"

        }

        viewModel.get_publishDate().observe(this,date_Observer)


        val press_Observer= Observer<String>{


           book_detail_publicpress.text = "出版社：$it"


        }

        viewModel.get_press().observe(this,press_Observer)


        val language__Observer= Observer<String>{

           book_detail_publiclanguge.text =  "語言：$it"

        }

        viewModel.get_language().observe(this,language__Observer)


        val ISBN_Observer = Observer<String>{

            book_detail_publicISBN.text =  "ISBN：$it"

        }

        viewModel.get_ISBN_string().observe(this,ISBN_Observer)

        return rootView
    }

}
