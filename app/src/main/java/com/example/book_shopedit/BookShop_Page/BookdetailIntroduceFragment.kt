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
import kotlinx.android.synthetic.main.fragment_bookdetail_introduce.*

/**
 * A simple [Fragment] subclass.
 */
class BookdetailIntroduceFragment : Fragment() {

    private val viewModel by lazy {

        ViewModelProviders.of(activity!!).get(ViewModel_BookDetail::class.java)

    }

    lateinit var rootView:View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_bookdetail_introduce, container, false)

        val introduce_Observer = Observer<String>{

            book_detail_introduce.text = it

        }

        viewModel.get_introduce().observe(this,introduce_Observer)

        return rootView
    }

}
