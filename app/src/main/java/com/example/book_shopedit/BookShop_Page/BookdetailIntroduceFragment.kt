package com.example.book_shopedit.BookShop_Page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.book_shopedit.R

/**
 * A simple [Fragment] subclass.
 */
class BookdetailIntroduceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookdetail_introduce, container, false)
    }

}
