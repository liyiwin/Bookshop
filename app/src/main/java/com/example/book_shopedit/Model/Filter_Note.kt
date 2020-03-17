package com.example.book_shopedit.Model

import com.example.book_shopedit.Data.my_data_book_note

fun filter_note(l:MutableList<my_data_book_note>):MutableList<my_data_book_note>{

     val l2 = mutableListOf<my_data_book_note>()

    for( i in 0 until l.size){

        val reading_content_id = l[i].reading_content_id

        val list = l.filter{it.reading_content_id == reading_content_id}

        // 代表 具這個 reading_content_id  的list 不只一個

        if (list.size > 1){

        val l3 = l2.filter {it.reading_content_id == reading_content_id }

         // l2 完全沒加過同 reading_content_id 的 list 才能加入（避免重複）

         if(l3.size == 0){

             l2.add(list[0])

         }
     }

        // 代表 具這個 reading_content_id  的list 只有一個

        else{

            l2.addAll(list)

        }


    }

    return l2

}